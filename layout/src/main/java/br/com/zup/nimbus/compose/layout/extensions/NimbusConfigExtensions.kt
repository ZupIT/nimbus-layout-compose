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
