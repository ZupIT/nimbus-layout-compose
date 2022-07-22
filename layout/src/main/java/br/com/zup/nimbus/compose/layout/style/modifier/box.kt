package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Box

internal fun Modifier.boxStyle(style: Box) = this.then(
    marginStyle(style)
    .sizeStyle(style)
    .shadowStyle(style)
    .borderStyle(style)
    .background(style.backgroundColor)
    .paddingStyle(style)
)
