/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.nimbus.compose.layout.component.container

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import br.com.zup.nimbus.compose.deserialization.DeserializationContext
import br.com.zup.nimbus.compose.layout.ComponentNames
import br.com.zup.nimbus.core.tree.ServerDrivenNode

@Composable
@AutoDeserialize
internal fun LazyColumn(
    @Root style: Box,
    crossAxisAlignment: CrossAxisAlignment?,
    mainAxisAlignment: MainAxisAlignment?,
    context: DeserializationContext,
) {
    val verticalArrangement = (mainAxisAlignment ?: MainAxisAlignment.Start).toVerticalArrangement()
    val horizontalAlignment = (crossAxisAlignment ?: CrossAxisAlignment.Start).toHorizontalAlignment()
    val content = remember(context.component?.node?.children?.map { it.id }) {
        context.component?.childrenAsList?.invoke() ?: emptyList()
    }
    val stickyHeaderGroup: LinkedHashMap<(@Composable () -> Unit)?, MutableList<(@Composable () -> Unit)?>> =
        groupStickyHeaders(context, content)

    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        LazyColumn(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            contentPadding = style.padding.toPaddingValues(),
            modifier = Modifier.boxStyle(style.copy(omitPadding = true)),
        ) {

            renderContentGroup(stickyHeaderGroup)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyListScope.renderContentGroup(
    contentGroup: LinkedHashMap<(@Composable () -> Unit)?, MutableList<(@Composable () -> Unit)?>>
) {
    contentGroup.forEach { (t, u) ->
        if (t != null) {
            stickyHeader {
                t.invoke()
            }
        }

        items(items = u) { item ->
            item?.invoke()
        }
    }
}

/**
 * Returns a map where the key is a header and the value of the maps is the content associated
 * with that header.
 * If theres no header for the content, the key will be null
 */
internal fun groupStickyHeaders(
    context: DeserializationContext,
    content: List<@Composable () -> Unit>,
): LinkedHashMap<(@Composable () -> Unit)?, MutableList<(@Composable () -> Unit)?>> {
    val stickyHeaders: LinkedHashMap<(@Composable () -> Unit)?, MutableList<(@Composable () -> Unit)?>> = LinkedHashMap()
    context.component?.node?.children?.forEachIndexed { index, serverDrivenNode ->
        if (stickyHeaders.isEmpty() && serverDrivenNode.component.endsWith(ComponentNames.STICKY_HEADER)
                .not()
        ) {
            stickyHeaders[null] =
                mutableListOf(
                    content[index])
        } else if (serverDrivenNode.component.endsWith(ComponentNames.STICKY_HEADER)) {
            stickyHeaders[content[index]] = mutableListOf()
        } else {
            stickyHeaders[stickyHeaders.keys.last()]?.add(
                content[index])
        }
    }
    return stickyHeaders
}
