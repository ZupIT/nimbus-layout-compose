package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.extensions.box
import br.com.zup.nimbus.compose.layout.model.BoxModel
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

@Composable
internal fun Stack(
    model: BoxModel,
    modifier: Modifier = Modifier,
    content: Component,
) {
    NimbusSoftwareLayer(condition = model.shouldDisableHardwareAcceleration()) {
        Box(
            modifier = modifier.box(model)
        ) {
            content()
        }
    }
}
