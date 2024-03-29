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

package br.com.zup.nimbus.compose.layout

import android.graphics.Color
import android.util.Log

internal const val COLOR_WHITE = "#FFFFFF"

internal const val RGB_LENGTH = 4
internal const val RGBA_LENGTH = 9
internal object ColorUtils {

    fun hexColor(hexColor: String): Int {
        return try {
            when (hexColor.length) {
                RGB_LENGTH -> Color.parseColor(formatHexRGBColor(hexColor))
                RGBA_LENGTH -> Color.parseColor(formatHexColorAlpha(hexColor))
                else -> Color.parseColor(formatHexRGBAColor(hexColor))
            }
        } catch (e: IllegalArgumentException) {
            Log.d("Color", "$hexColor is not a valid color. Using black instead.")
            Color.BLACK
        }
    }

    private fun formatHexColorAlpha(color: String): String {
        return "^#([0-9A-F]{6})([0-9A-F]{2})$"
            .toRegex(RegexOption.IGNORE_CASE)
            .replace(color, "#\$2\$1")
    }

    private fun formatHexRGBColor(color: String): String {
        return "^#([0-9A-F])([0-9A-F])([0-9A-F])?$"
            .toRegex(RegexOption.IGNORE_CASE)
            .replace(color, "#\$1\$1\$2\$2\$3\$3")
    }

    private fun formatHexRGBAColor(color: String): String {
        return "^#([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])?$"
            .toRegex(RegexOption.IGNORE_CASE)
            .replace(color, "#\$4\$4\$1\$1\$2\$2\$3\$3")
    }
}
