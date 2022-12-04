package br.com.zup.nimbus.compose.layout.sample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import br.com.zup.nimbus.compose.layout.configuration.ImageProvider
import br.com.zup.nimbus.core.network.DefaultHttpClient
import br.com.zup.nimbus.core.network.HttpClient
import br.com.zup.nimbus.core.network.ServerDrivenHttpMethod
import br.com.zup.nimbus.core.network.ServerDrivenRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class DefaultImageProvider(
    private val httpClient: HttpClient = DefaultHttpClient(),
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
): ImageProvider {

    override fun fetchRemote(url: String, onFetch: (Bitmap) -> Unit, onError: (Throwable) -> Unit) {
        coroutineScope.launch {
            try {
                val response = httpClient.sendRequest(ServerDrivenRequest(
                    url = url,
                    method = ServerDrivenHttpMethod.Get,
                    headers = null, body = null))
                response.let {
                    val bitmap = BitmapFactory.decodeByteArray(
                        it.bodyBytes,
                        0,
                        it.bodyBytes.size)
                    onFetch(bitmap)
                }
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

    override fun fetchLocal(id: String): Int? = when (id) {
        "nimbus-local" -> R.drawable.nimbus_local
        "placeholder-img" -> R.drawable.placeholder_img
        else -> null
    }
}
