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

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.Padding

internal fun Modifier.paddingStyle(style: Padding): Modifier {
    var paddingStart: Double? = null
    var paddingEnd: Double? = null
    var paddingTop: Double? = null
    var paddingBottom: Double? = null

    style.padding?.let {
        paddingStart = it
        paddingEnd = it
        paddingTop = it
        paddingBottom = it
    }

    style.paddingStart?.let {
        paddingStart = it
    }

    style.paddingEnd?.let {
        paddingEnd = it
    }

    style.paddingTop?.let {
        paddingTop = it
    }

    style.paddingBottom?.let {
        paddingBottom = it
    }

    style.paddingHorizontal?.let {
        paddingStart = it
        paddingEnd = it
    }

    style.paddingVertical?.let {
        paddingTop = it
        paddingBottom = it
    }

    return this.padding(
        start = paddingStart?.dp ?: 0.dp,
        end = paddingEnd?.dp ?: 0.dp,
        top = paddingTop?.dp ?: 0.dp,
        bottom = paddingBottom?.dp ?: 0.dp,
    )
}
