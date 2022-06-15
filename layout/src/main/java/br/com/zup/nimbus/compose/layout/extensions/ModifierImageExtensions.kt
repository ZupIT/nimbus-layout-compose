package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.BaseImage

internal fun Modifier.image(
    image: BaseImage,
    modifier: Modifier = Modifier,
) = this.then(
    modifier
        .clipped(image.clipped)
        .size(image)
)
