package br.com.zup.nimbus.compose.layout.extensions

import br.com.zup.nimbus.compose.layout.configuration.DefaultImageProvider
import br.com.zup.nimbus.compose.layout.configuration.ImageProvider
import br.com.zup.nimbus.compose.Nimbus

private const val NIMBUS_IMAGE_PROVIDER_KEY = "NIMBUS_IMAGE_PROVIDER_KEY"

fun Nimbus.imageProvider(imageProvider: ImageProvider): Nimbus {
    set(
        key = NIMBUS_IMAGE_PROVIDER_KEY,
        value = imageProvider
    )
    return this
}

internal fun Nimbus.imageProvider(): ImageProvider {
    val current = get(key = NIMBUS_IMAGE_PROVIDER_KEY)
    return if (current is ImageProvider) current else DefaultImageProvider()
}
