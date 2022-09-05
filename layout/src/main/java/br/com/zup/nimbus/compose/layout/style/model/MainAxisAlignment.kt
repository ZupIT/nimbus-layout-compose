package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.foundation.layout.Arrangement

internal enum class MainAxisAlignment {
    Start,
    End,
    Center,
    SpaceBetween,
    SpaceAround,
    SpaceEvenly;

    companion object {
        fun deserialize(alignment: Any?): MainAxisAlignment? {
            if (alignment == null || alignment !is String) return null
            return values().firstOrNull { it.name.lowercase() == alignment }
        }
    }

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
