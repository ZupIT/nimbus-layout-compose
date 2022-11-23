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
