package br.com.zup.nimbus.compose.layout.component.image

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModel
import br.zup.com.nimbus.compose.NimbusTheme
import com.zup.nimbus.processor.annotation.AutoDeserialize
import com.zup.nimbus.processor.annotation.Ignore
import com.zup.nimbus.processor.annotation.Root

@Composable
@AutoDeserialize
internal fun LocalImage(
    @Root style: Size,
    scale: ImageScale?,
    id: String,
    accessibility: Accessibility? = null,
    @Ignore viewModel: ImageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = NimbusTheme.nimbus.imageProvider()
        )
    )
) {
    Image(
        viewModel = viewModel,
        isLocal = true,
        scale = scale,
        id = id,
        style = style,
        accessibility = accessibility,
    )
}
