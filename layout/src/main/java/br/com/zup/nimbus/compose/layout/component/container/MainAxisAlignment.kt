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

import androidx.compose.foundation.layout.Arrangement

internal enum class MainAxisAlignment {
    Start,
    End,
    Center,
    SpaceBetween,
    SpaceAround,
    SpaceEvenly;

    fun toHorizontalArrangement(): Arrangement.Horizontal =
        when (this) {
            Start -> Arrangement.Start
            End -> Arrangement.End
            Center -> Arrangement.Center
            SpaceBetween -> Arrangement.SpaceBetween
            SpaceAround -> Arrangement.SpaceAround
            SpaceEvenly -> Arrangement.SpaceEvenly
        }

    fun toVerticalArrangement(): Arrangement.Vertical =
        when (this) {
            Start -> Arrangement.Top
            End -> Arrangement.Bottom
            Center -> Arrangement.Center
            SpaceBetween -> Arrangement.SpaceBetween
            SpaceAround -> Arrangement.SpaceAround
            SpaceEvenly -> Arrangement.SpaceEvenly
        }
}
