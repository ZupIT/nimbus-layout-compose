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

import br.com.zup.nimbus.annotation.Root

internal open class Box(
    @Root val margin: Margin,
    @Root val padding: Padding,
    @Root val border: Border,
    @Root val size: Size,
    val backgroundColor: String?,
    val shadow: List<Shadow>?,
) {
    fun shouldDisableHardwareAcceleration(): Boolean {
        return shadow?.let { shadowList ->
            shadowList.any { it.blur != null && it.blur > 0.0 }
        } ?: false
    }

    fun copy(
        omitMargin: Boolean = false,
        omitPadding: Boolean = false,
        omitBorder: Boolean = false,
        omitSize: Boolean = false,
        omitBackgroundColor: Boolean = false,
        omitShadow: Boolean = false,
    ) = Box(
        margin = if (omitMargin) Margin.empty else margin,
        padding = if (omitPadding) Padding.empty else padding,
        border = if (omitBorder) Border.empty else border,
        size = if (omitSize) Size.empty else size,
        backgroundColor = if (omitBackgroundColor) null else backgroundColor,
        shadow = if (omitShadow) null else shadow,
    )
}
