package br.com.zup.nimbus.compose.layout.component.image

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModel
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Ignore
import br.com.zup.nimbus.annotation.Root
import br.zup.com.nimbus.compose.Nimbus

@Composable
@AutoDeserialize
internal fun LocalImage(
    @Root style: Size,
    scale: ImageScale?,
    id: String,
    accessibility: Accessibility? = null,
    @Ignore viewModel: ImageViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ImageViewModel.provideFactory(
            imageProvider = Nimbus.instance.imageProvider()
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
