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

package br.com.zup.nimbus.compose.layout.style.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.AdaptiveSize
import br.com.zup.nimbus.compose.layout.style.model.DirectionScope
import br.com.zup.nimbus.compose.layout.style.model.Size

private object ScopeWithWeight {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

private fun applyWeight(modifier: Modifier): Modifier {
    // trick compose into thinking it's inside a column scope
    return when (val scope = ScopeWithWeight.instance) {
        is ColumnScope -> with(scope) { modifier.weight(1F) }
        else -> modifier
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
private fun handleAdaptiveWidth(modifier: Modifier, size: Size): Modifier {
    return when (size.width) {
        AdaptiveSize.Expand -> {
            if (size.maxWidth != null) {
                modifier.widthIn(max = size.maxWidth.dp)
            } else if (size.directionScope == DirectionScope.Row) {
                applyWeight(modifier)
            } else {
                modifier.fillMaxWidth()
            }
        }
        AdaptiveSize.FitContent, null -> {
            var changedModifier = modifier
            size.minWidth?.let { changedModifier = changedModifier.widthIn(min = it.dp) }
            size.maxWidth?.let { changedModifier = changedModifier.widthIn(max = it.dp) }
            changedModifier
        }
        is AdaptiveSize.Fixed -> modifier.width(size.width.value.dp)
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
private fun handleAdaptiveHeight(modifier: Modifier, size: Size): Modifier {
    return when (size.height) {
        AdaptiveSize.Expand -> {
            if (size.maxHeight != null) {
                modifier.heightIn(max = size.maxHeight.dp)
            } else if (size.directionScope == DirectionScope.Column) {
                applyWeight(modifier)
            } else {
                modifier.fillMaxHeight()
            }
        }
        AdaptiveSize.FitContent, null -> {
            var changedModifier = modifier
            size.minHeight?.let { changedModifier = changedModifier.heightIn(min = it.dp) }
            size.maxHeight?.let { changedModifier = changedModifier.heightIn(max = it.dp) }
            changedModifier
        }
        is AdaptiveSize.Fixed -> modifier.height(size.height.value.dp)
    }
}

internal fun Modifier.sizeStyle(style: Size): Modifier {
    var current = handleAdaptiveWidth(this, style)
    current = handleAdaptiveHeight(current, style)
    if (style.clipped == true) current = current.clipToBounds()
    return current
}
