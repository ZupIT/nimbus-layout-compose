package br.com.zup.nimbus.compose.layout.configuration

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import br.com.zup.nimbus.core.network.DefaultHttpClient
import br.com.zup.nimbus.core.network.HttpClient
import br.com.zup.nimbus.core.network.ServerDrivenHttpMethod
import br.com.zup.nimbus.core.network.ServerDrivenRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class DefaultImageProvider(
    private val httpClient: HttpClient = DefaultHttpClient(),
): ImageProvider {
    override suspend fun fetchRemote(url: String): Bitmap {
        val response = httpClient.sendRequest(ServerDrivenRequest(
            url = url,
            method = ServerDrivenHttpMethod.Get,
            headers = null, body = null))
        return response.let {
            BitmapFactory.decodeByteArray(
                it.bodyBytes,
                0,
                it.bodyBytes.size)
        }
    }

    override fun fetchLocal(id: String): Int? = null
}
