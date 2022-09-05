package br.com.zup.nimbus.compose.layout.style.model

import br.com.zup.nimbus.compose.layout.COLOR_BLACK

data class Shadow(
    val x: Double? = 0.0, // default: 0
    val y: Double? = 0.0, // default: 0
    val blur: Double? = 0.0, // default: 0
    val color: String? = COLOR_BLACK, // default: black
    val spread: Int? = null, // only if it's easy to do in SwiftUI
    val inset: Boolean? = null // seems difficult, at first we will not implement
)
