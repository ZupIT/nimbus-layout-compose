package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.box
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.NimbusBoxApi
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration
import com.google.accompanist.flowlayout.FlowColumn

@Composable
internal fun NimbusFlowColumn(
    model: NimbusBoxApi,
    modifier: Modifier = Modifier,
    content: Component,
) {
    val column = requireNotNull(model.properties)
    NimbusSoftwareLayer(condition = column.shouldDisableHardwareAcceleration()) {
        FlowColumn(
            modifier = modifier.box(column)
        ) {
            content()
        }
    }
}


