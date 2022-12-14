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

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.Positioned
import br.com.zup.nimbus.compose.layout.style.model.PositionedAlignment

internal fun Modifier.positionedStyle(
    style: Positioned,
    @LayoutScopeMarker
    scope: Any,
) = this.then(
    Modifier.applyScopeModifier(scope = scope, positioned = style)
    .absoluteOffset(x = style.x?.dp ?: 0.dp)
    .absoluteOffset(y = style.y?.dp ?: 0.dp)
    .boxStyle(style.box)
)

internal fun Modifier.applyScopeModifier(
    scope: Any? = null,
    positioned: Positioned,
    modifier: Modifier = Modifier,
) = this.then(with(positioned) {
    if (scope == null) return@with modifier
    var newModifier = modifier
    when (scope) {
        is BoxScope -> {
            with(scope)
            {
                val nimbusAlignment = positioned.alignment ?: PositionedAlignment.TopStart
                val alignment = nimbusAlignment.toAlignment()
                newModifier = newModifier.align(alignment)
            }
        }
    }
    return@with newModifier
})
