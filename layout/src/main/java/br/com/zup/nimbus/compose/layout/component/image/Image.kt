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

package br.com.zup.nimbus.compose.layout.component.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModel
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModelState
import br.com.zup.nimbus.compose.Nimbus

@Composable
internal fun Image(
    style: Size,
    scale: ImageScale?,
    id: String? = null,
    url: String? = null,
    placeholder: String? = null,
    accessibility: Accessibility?,
    isLocal: Boolean,
    viewModel: ImageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = Nimbus.instance.imageProvider()
        )
    ),
) {
    var viewModelState: ImageViewModelState by remember {
        mutableStateOf(ImageViewModelState.Nothing)
    }

    LaunchedEffect(url, id) {
        if (isLocal) {
           viewModel.fetchLocalImage(id ?: "")?.let { viewModelState = it }
        } else {
            placeholder?.let { placeholderId ->
                viewModel.fetchLocalImage(placeholderId, isPlaceholder = true)?.let {
                    viewModelState = it
                }
            }
            viewModelState = viewModel.fetchRemoteImage(url ?: "")
        }
    }

    ImageRenderer(
        viewModelState = viewModelState,
        style = style,
        contentScale = scale?.toContentScale(),
        accessibility = accessibility,
    )
}
