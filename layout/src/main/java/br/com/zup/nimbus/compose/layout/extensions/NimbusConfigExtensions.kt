package br.com.zup.nimbus.compose.layout.extensions

import br.com.zup.nimbus.compose.layout.configuration.DefaultImageProvider
import br.com.zup.nimbus.compose.layout.configuration.ImageProvider
import br.zup.com.nimbus.compose.Nimbus

private const val NIMBUS_IMAGE_PROVIDER_KEY = "NIMBUS_IMAGE_PROVIDER_KEY"

fun Nimbus.imageProvider(imageProvider: ImageProvider) = environmentObject(
    key = NIMBUS_IMAGE_PROVIDER_KEY,
    value = imageProvider
)

internal fun Nimbus.imageProvider(): ImageProvider =
    environmentObject(key = NIMBUS_IMAGE_PROVIDER_KEY) ?: DefaultImageProvider()
