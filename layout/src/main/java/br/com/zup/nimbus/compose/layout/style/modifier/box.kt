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

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Box

internal fun Modifier.boxStyle(style: Box) = this.then(
    Modifier.marginStyle(style.margin)
    .sizeStyle(style.size)
    .shadowStyle(style)
    .borderStyle(style.border)
    .background(style.backgroundColor)
    .paddingStyle(style.padding)
)
