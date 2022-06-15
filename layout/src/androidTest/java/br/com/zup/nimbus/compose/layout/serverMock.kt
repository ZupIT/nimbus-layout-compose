package br.com.zup.nimbus.compose.layout

import br.com.zup.nimbus.android.layout.test.R
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.ByteReadChannel

const val BASE_URL = "http://localhost"

private val NIMBUS_IMAGE_BYTES by lazy { getContext().readImageAsBytes(R.drawable.nimbus_local) }

val serverMock = MockEngine { request ->
    return@MockEngine when(request.url.toString()) {
        "$BASE_URL/nimbusimage" -> respond(
            content = ByteReadChannel(content = NIMBUS_IMAGE_BYTES),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "image/png")
        )
        else -> respond(
            content = ByteReadChannel(""),
            status = HttpStatusCode.NotFound,
        )
    }
}
