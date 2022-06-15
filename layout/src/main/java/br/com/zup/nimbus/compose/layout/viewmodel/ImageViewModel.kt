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
