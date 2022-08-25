package br.com.zup.nimbus.compose.layout.component.text

import androidx.compose.ui.text.font.FontWeight

internal enum class TextWeight {
    Thin,
    ExtraLight,
    Light,
    Normal,
    Medium,
    SemiBold,
    Bold,
    ExtraBold,
    Black;


    fun toFontWeight(): FontWeight =
        when (this) {
            Thin -> FontWeight.Thin
            ExtraLight -> FontWeight.ExtraLight
            Light -> FontWeight.Light
            Normal -> FontWeight.Normal
            Medium -> FontWeight.Medium
            SemiBold -> FontWeight.SemiBold
            Bold -> FontWeight.Bold
            ExtraBold -> FontWeight.ExtraBold
            Black -> FontWeight.Black
        }
}
