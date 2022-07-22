package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.ui.graphics.Color
import br.com.zup.nimbus.compose.layout.ColorUtils

val String.color
    get() = Color(ColorUtils.hexColor(this))
