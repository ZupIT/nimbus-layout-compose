package br.com.zup.nimbus.compose.layout

import android.graphics.Color

internal const val RGB_LENGTH = 4
internal const val RGBA_LENGTH = 9
internal object ColorUtils {

    fun hexColor(hexColor: String): Int {
        return when (hexColor.length) {
            RGB_LENGTH -> Color.parseColor(formatHexRGBColor(hexColor))
            RGBA_LENGTH -> Color.parseColor(formatHexColorAlpha(hexColor))
            else -> Color.parseColor(formatHexRGBAColor(hexColor))
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
