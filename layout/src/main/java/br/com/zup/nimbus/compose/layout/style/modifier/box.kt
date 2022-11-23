package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Box

internal fun Modifier.boxStyle(style: Box) = this.then(
    Modifier.marginStyle(style.margin)
    .sizeStyle(style.size)
    .shadowStyle(style)
    .borderStyle(style.border)
    .background(style.backgroundColor)
    .paddingStyle(style.padding)
)
