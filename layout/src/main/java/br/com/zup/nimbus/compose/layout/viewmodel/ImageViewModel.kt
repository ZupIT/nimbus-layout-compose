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

package br.com.zup.nimbus.compose.layout.viewmodel

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.zup.nimbus.compose.layout.configuration.ImageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal sealed class ImageViewModelState {
    object Nothing : ImageViewModelState()
    class BitmapImage(val bitmap: Bitmap) : ImageViewModelState()
    class Error(val throwable: Throwable) : ImageViewModelState()
    class ImageResource(@DrawableRes val id: Int, val isPlaceholder: Boolean = false) : ImageViewModelState()
}

internal class ImageViewModel(val imageProvider: ImageProvider) : ViewModel() {

    companion object {
        fun provideFactory(
            imageProvider: ImageProvider,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ImageViewModel(
                    imageProvider = imageProvider
                ) as T
            }
        }
    }

    fun fetchRemoteImage(url: String, onResult: (ImageViewModelState) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            imageProvider.fetchRemote(
                url,
                onFetch = {
                    onResult(ImageViewModelState.BitmapImage(bitmap = it))
                },
                onError = {
                    onResult(ImageViewModelState.Error(throwable = it))
                })
        }

    fun fetchLocalImage(id: String, isPlaceholder: Boolean = false, onResult: (ImageViewModelState) -> Unit) {
        try {
            val resourceId = imageProvider.fetchLocal(id)
            resourceId?.let {
                onResult(ImageViewModelState.ImageResource(id = it, isPlaceholder = isPlaceholder))
            }
        } catch (e: Throwable) {
            onResult(ImageViewModelState.Error(throwable = e))
        }
    }
}
