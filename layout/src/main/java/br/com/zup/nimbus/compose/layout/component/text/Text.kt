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

package br.com.zup.nimbus.compose.layout.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import br.com.zup.nimbus.compose.layout.extensions.color
import br.com.zup.nimbus.annotation.AutoDeserialize

private const val DEFAULT_TEXT_SIZE = 12L

@OptIn(ExperimentalUnitApi::class)
@Composable
@AutoDeserialize
internal fun Text(
    text: String?,
    size: Double?,
    weight: TextWeight?,
    color: Color?,
    alignment: TextAlignment?,
) {
    Text(
        text = text ?: "",
        color = color ?: Color.Black,
        fontSize = TextUnit((size?.toLong() ?: DEFAULT_TEXT_SIZE).toFloat(), TextUnitType.Sp),
        fontWeight = (weight ?: TextWeight.Normal).toFontWeight(),
        textAlign = when (alignment) {
            TextAlignment.Start -> TextAlign.Start
            TextAlignment.End -> TextAlign.End
            TextAlignment.Center -> TextAlign.Center
            else -> TextAlign.Start
        }
    )
}
