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

    LaunchedEffect(url) {
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
