package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Container

internal fun Modifier.containerStyle(
    style: Container,
    @Suppress("UNUSED_PARAMETER") parentComponentName: String? = null,
) = this.then(
    Modifier.marginStyle(style)
    .sizeStyle(style)
    .shadowStyle(style)
    .borderStyle(style)
    .background(style.backgroundColor)
    .paddingStyle(style)
)
