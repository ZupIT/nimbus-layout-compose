package br.com.zup.nimbus.compose.layout.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.zup.nimbus.compose.layout.extensions.accessibility
import br.com.zup.nimbus.compose.layout.extensions.image
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.model.BaseImage
import br.com.zup.nimbus.compose.layout.model.LocalImageModel
import br.com.zup.nimbus.compose.layout.model.RemoteImageModel
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModel
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModelState
import br.zup.com.nimbus.compose.NimbusTheme

private sealed class Image {
    class Local(val localImage: LocalImageModel) : Image()
    class Remote(val remoteImage: RemoteImageModel) : Image()
}

@Composable
internal fun LocalImage(
    model: LocalImageModel,
    modifier: Modifier = Modifier,
    viewModel: ImageViewModel = viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = NimbusTheme.nimbus.imageProvider()
        )
    )
) {
    NimbusImageImpl(modifier = modifier, viewModel = viewModel, model = Image.Local(model))
}

@Composable
internal fun RemoteImage(
    model: RemoteImageModel,
    modifier: Modifier = Modifier,
    viewModel: ImageViewModel = viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = NimbusTheme.nimbus.imageProvider()
        )
    )
) {
    NimbusImageImpl(modifier = modifier, viewModel = viewModel, model = Image.Remote(model))
}

private const val LOG_TAG = "NimbusImage"

@Composable
private fun NimbusImageImpl(
    modifier: Modifier = Modifier,
    viewModel: ImageViewModel = viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = NimbusTheme.nimbus.imageProvider()
        )
    ),
    model: Image,
) {
    var viewModelState: ImageViewModelState by remember {
        mutableStateOf(ImageViewModelState.Nothing)
    }
    var contentScale: ContentScale? = null
    var baseImage: BaseImage? = null

    when (model) {
        is Image.Local -> {
            baseImage = model.localImage
            contentScale = model.localImage.scale?.toContentScale()
            if (viewModelState == ImageViewModelState.Nothing) {
                viewModel.fetchLocalImage(model.localImage.id ?: "") {
                    viewModelState = it
                }
            }
        }
        is Image.Remote -> {
            baseImage = model.remoteImage
            contentScale = model.remoteImage.scale?.toContentScale()
            if (viewModelState == ImageViewModelState.Nothing) {
                model.remoteImage.placeholder?.let { placeholder ->
                    viewModel.fetchLocalImage(placeholder, isPlaceholder = true) {
                        viewModelState = it
                    }
                }
                viewModel.fetchRemoteImage(model.remoteImage.url ?: "") {
                    viewModelState = it
                }
            }
        }
    }

    RenderImage(modifier = modifier,
        viewModelState = viewModelState,
        baseImage = baseImage,
        contentScale = contentScale)

}

@Composable
private fun RenderImage(
    modifier: Modifier = Modifier,
    viewModelState: ImageViewModelState,
    baseImage: BaseImage?,
    contentScale: ContentScale?,
) {

    val image = requireNotNull(baseImage)
    var newModifier = modifier
    newModifier = newModifier.image(image)

    when (viewModelState) {
        is ImageViewModelState.ImageResource -> {
            var description: String? = null
            if (!viewModelState.isPlaceholder) {
                description = image.accessibility?.label
                newModifier = newModifier.accessibility(image.accessibility)
            }
            Image(
                painter = painterResource(viewModelState.id),
                contentDescription = description,
                modifier = newModifier,
                contentScale = contentScale ?: ContentScale.Inside
            )
        }
        is ImageViewModelState.Error -> {
            val throwable = viewModelState.throwable
            Log.d(LOG_TAG, throwable.message ?: "", throwable)
            EmptyBox(newModifier)
        }
        is ImageViewModelState.BitmapImage -> {
            newModifier = newModifier.accessibility(image.accessibility)
            Image(
                bitmap = viewModelState.bitmap.asImageBitmap(),
                contentDescription = image.accessibility?.label,
                modifier = newModifier,
                contentScale = contentScale ?: ContentScale.Inside
            )
        }
        is ImageViewModelState.Nothing -> {
        }
    }
}

@Composable
private fun EmptyBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

    }
}