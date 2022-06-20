package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Box

internal fun Modifier.box(
    box: Box,
    modifier: Modifier = Modifier,
) = this.then(
    modifier
        .margin(box)
        .size(box)
        .shadow(box)
        .border(box)
        .background(box.backgroundColor)
        .padding(box)
)
