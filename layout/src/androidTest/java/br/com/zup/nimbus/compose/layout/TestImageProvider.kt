package br.com.zup.nimbus.compose.layout

import br.com.zup.nimbus.android.layout.test.R
import br.com.zup.nimbus.compose.layout.configuration.DefaultImageProvider
import br.com.zup.nimbus.compose.layout.configuration.ImageProvider
import br.com.zup.nimbus.core.network.DefaultHttpClient
import br.com.zup.nimbus.core.network.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

internal class TestImageProvider(
    httpClient: HttpClient = DefaultHttpClient(serverMock),
): DefaultImageProvider(httpClient) {
    override fun fetchLocal(id: String): Int? = when (id) {
        "nimbus-local" -> R.drawable.nimbus_local
        "placeholder-img" -> R.drawable.placeholder_img
        else -> null
    }
}
