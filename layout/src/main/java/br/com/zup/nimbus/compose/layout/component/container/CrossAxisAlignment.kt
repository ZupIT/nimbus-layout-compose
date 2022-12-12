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

import androidx.compose.ui.Alignment

internal enum class CrossAxisAlignment {
    Start,
    End,
    Center;

    fun toVerticalAlignment(): Alignment.Vertical =
        when (this) {
            Start -> Alignment.Top
            End -> Alignment.Bottom
            Center -> Alignment.CenterVertically
        }

    fun toHorizontalAlignment(): Alignment.Horizontal =
        when (this) {
            Start -> Alignment.Start
            End -> Alignment.End
            Center -> Alignment.CenterHorizontally
        }
}
