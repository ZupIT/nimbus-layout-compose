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

    suspend fun fetchRemoteImage(url: String): ImageViewModelState =
        try {
            val bitmap = imageProvider.fetchRemote(url)
            ImageViewModelState.BitmapImage(bitmap)
        } catch (throwable: Throwable) {
            ImageViewModelState.Error(throwable)
        }

    fun fetchLocalImage(id: String, isPlaceholder: Boolean = false) =
        try {
            val resourceId = imageProvider.fetchLocal(id)
            resourceId?.let {
                ImageViewModelState.ImageResource(id = it, isPlaceholder = isPlaceholder)
            }
        } catch (e: Throwable) {
            ImageViewModelState.Error(throwable = e)
        }
    }
