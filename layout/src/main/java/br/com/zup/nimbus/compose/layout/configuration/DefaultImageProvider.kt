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
