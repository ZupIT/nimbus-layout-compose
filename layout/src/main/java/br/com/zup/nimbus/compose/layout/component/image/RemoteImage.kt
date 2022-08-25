package br.com.zup.nimbus.compose.layout.component.image

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.deserialization.AccessibilityDeserializer
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModel
import br.zup.com.nimbus.compose.NimbusTheme
import com.zup.nimbus.processor.Computed
import com.zup.nimbus.processor.Ignore
import com.zup.nimbus.processor.Root
import com.zup.nimbus.processor.ServerDrivenComponent

@Composable
@ServerDrivenComponent
internal fun RemoteImage(
    @Root style: Size?,
    scale: ImageScale?,
    url: String,
    placeholder: String?,
    @Computed<AccessibilityDeserializer>(AccessibilityDeserializer::class) accessibility: Accessibility? = null,
    @Ignore viewModel: ImageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = NimbusTheme.nimbus.imageProvider()
        )
    )
) {
    Image(
        isLocal = false,
        url = url,
        placeholder = placeholder,
        viewModel = viewModel,
        style = style,
        accessibility = accessibility,
        scale = scale,
    )
}