package br.com.zup.nimbus.compose.layout.component.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.deserialization.SizeDeserializer
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModel
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModelState
import br.zup.com.nimbus.compose.NimbusTheme
import com.zup.nimbus.processor.Computed

@Composable
internal fun Image(
    @Computed<SizeDeserializer>(SizeDeserializer::class) style: Size?,
    scale: ImageScale?,
    id: String? = null,
    url: String? = null,
    placeholder: String? = null,
    accessibility: Accessibility?,
    isLocal: Boolean,
    viewModel: ImageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = NimbusTheme.nimbus.imageProvider()
        )
    ),
) {
    var viewModelState: ImageViewModelState by remember {
        mutableStateOf(ImageViewModelState.Nothing)
    }
    val contentScale = scale?.toContentScale()

    if (isLocal && viewModelState == ImageViewModelState.Nothing) {
       viewModel.fetchLocalImage(id ?: "") { viewModelState = it }
    } else if (viewModelState == ImageViewModelState.Nothing) {
        placeholder?.let { placeholderId ->
            viewModel.fetchLocalImage(placeholderId, isPlaceholder = true) {
                viewModelState = it
            }
        }
        viewModel.fetchRemoteImage(url ?: "") {
            viewModelState = it
        }
    }

    ImageRenderer(
        viewModelState = viewModelState,
        style = style,
        contentScale = contentScale,
        accessibility = accessibility,
    )
}
