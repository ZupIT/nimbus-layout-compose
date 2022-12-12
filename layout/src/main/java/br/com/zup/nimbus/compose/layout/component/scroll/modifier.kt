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

package br.com.zup.nimbus.compose.layout.component.scroll

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier

internal fun Modifier.scroll(
    direction: ScrollViewDirection,
    scrollIndicator: Boolean,
    verticalScrollState: ScrollState,
    horizontalScrollState: ScrollState,
): Modifier {
    var newModifier = this
    when (direction) {
        ScrollViewDirection.Both -> {
            if (scrollIndicator) {
                newModifier = newModifier.drawVerticalScrollbar(verticalScrollState)
                newModifier = newModifier.drawHorizontalScrollbar(horizontalScrollState)
            }
            newModifier = newModifier.verticalScroll(verticalScrollState)
            newModifier = newModifier.horizontalScroll(horizontalScrollState)
        }
        ScrollViewDirection.Horizontal -> {
            if (scrollIndicator) {
                newModifier = newModifier.drawHorizontalScrollbar(horizontalScrollState)
            }
            newModifier = newModifier.horizontalScroll(horizontalScrollState)
        }
        ScrollViewDirection.Vertical -> {
            if (scrollIndicator) {
                newModifier = newModifier.drawVerticalScrollbar(verticalScrollState)
            }
            newModifier = newModifier.verticalScroll(verticalScrollState)
        }
    }

    return newModifier
}
