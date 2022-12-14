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

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root

@Composable
@AutoDeserialize
internal fun Column(
    @Root style: Box,
    crossAxisAlignment: CrossAxisAlignment?,
    mainAxisAlignment: MainAxisAlignment?,
    content: @Composable () -> Unit,
) {
    val verticalArrangement = (mainAxisAlignment ?: MainAxisAlignment.Start).toVerticalArrangement()
    val horizontalAlignment = (crossAxisAlignment ?: CrossAxisAlignment.Start).toHorizontalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = Modifier.boxStyle(style)
        ) {
            content()
        }
    }
}
