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

package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.ui.Alignment
import br.com.zup.nimbus.annotation.Root

internal class Positioned(
    @Root val box: Box,
    val alignment: PositionedAlignment?,
    val x: Double?,
    val y: Double?,
)

enum class PositionedAlignment {
    TopStart,
    TopEnd,
    BottomStart,
    BottomEnd,
    TopCenter,
    BottomCenter,
    CenterStart,
    CenterEnd,
    Center;

    fun toAlignment(): Alignment = when (this) {
        TopStart -> Alignment.TopStart
        TopEnd -> Alignment.TopEnd
        BottomStart -> Alignment.BottomStart
        BottomEnd -> Alignment.BottomEnd
        TopCenter -> Alignment.TopCenter
        BottomCenter -> Alignment.BottomCenter
        CenterStart -> Alignment.CenterStart
        CenterEnd -> Alignment.CenterEnd
        Center -> Alignment.Center
    }
}
