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

import kotlin.math.roundToInt

sealed class AdaptiveSize {
    object Expand : AdaptiveSize()
    object FitContent : AdaptiveSize()
    class Fixed(val value: Double): AdaptiveSize()

    override fun equals(other: Any?): Boolean {
        return this === other || (this is Fixed && other is Fixed && this.value == other.value)
    }

    override fun hashCode(): Int {
        return when(this) {
            is Fixed -> (this.value * 100000).roundToInt()
            Expand -> -1
            FitContent -> -2
        }
    }
}

enum class DirectionScope { Row, Column, Stack }

open class Size (
    val width: AdaptiveSize?,
    val height: AdaptiveSize?,
    val directionScope: DirectionScope,
    val minWidth: Double?,
    val minHeight: Double?,
    val maxWidth: Double?,
    val maxHeight: Double?,
    val clipped: Boolean?,
)
