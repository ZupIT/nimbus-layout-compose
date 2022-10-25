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
