package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.ui.Alignment

internal enum class CrossAxisAlignment {
    Start,
    End,
    Center;

    companion object {
        fun deserialize(alignment: Any?): CrossAxisAlignment? {
            if (alignment == null || alignment !is String) return null
            return values().firstOrNull { it.name.lowercase() == alignment }
        }
    }

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
