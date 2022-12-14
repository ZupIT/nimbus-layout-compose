/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
