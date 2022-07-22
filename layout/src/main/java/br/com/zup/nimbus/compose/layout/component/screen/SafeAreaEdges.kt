package br.com.zup.nimbus.compose.layout.component.screen

import androidx.compose.foundation.layout.WindowInsetsSides

internal enum class SafeAreaEdges {
    Top,
    Bottom,
    Trailing,
    Leading;

    fun toWindowInsetsSide(): WindowInsetsSides = when (this) {
        Top -> WindowInsetsSides.Top
        Bottom -> WindowInsetsSides.Bottom
        Trailing -> WindowInsetsSides.Right
        Leading -> WindowInsetsSides.Left
    }
}
