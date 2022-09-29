package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Container

internal fun Modifier.containerStyle(style: Container) = this.then(
    Modifier.marginStyle(style)
    .sizeStyle(style)
    .shadowStyle(style)
    .borderStyle(style)
    .background(style.backgroundColor)
    .paddingStyle(style)
)
