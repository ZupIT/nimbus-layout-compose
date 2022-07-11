package br.com.zup.nimbus.compose.layout.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.box
import br.com.zup.nimbus.compose.layout.model.BoxModel
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration
import com.google.accompanist.flowlayout.FlowColumn

@Composable
internal fun FlowColumn(
    model: BoxModel,
    modifier: Modifier = Modifier,
    content: Component,
) {
    NimbusSoftwareLayer(condition = model.shouldDisableHardwareAcceleration()) {
        FlowColumn(
            modifier = modifier.box(model)
        ) {
            content()
        }
    }
}


