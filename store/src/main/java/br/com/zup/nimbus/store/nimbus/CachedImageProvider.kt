package br.com.zup.nimbus.store.nimbus

import android.graphics.Bitmap
import br.com.zup.nimbus.compose.layout.configuration.DefaultImageProvider

/**
 * An image provider with a very simple in-memory caching mechanism for remote images.
 */
class CachedImageProvider: DefaultImageProvider() {
    private val cache = mutableMapOf<String, Bitmap>()

    override suspend fun fetchRemote(url: String): Bitmap = cache[url] ?: run {
        val bitmap = super.fetchRemote(url)
        cache[url] = bitmap
        bitmap
    }
}
