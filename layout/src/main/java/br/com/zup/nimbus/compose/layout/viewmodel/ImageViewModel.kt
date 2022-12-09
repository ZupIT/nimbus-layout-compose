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
