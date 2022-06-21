package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.box
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.NimbusBoxApi
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration
import com.google.accompanist.flowlayout.FlowRow

@Composable
internal fun NimbusFlowRow(
    model: NimbusBoxApi,
    modifier: Modifier = Modifier,
    content: Component,
) {
    val row = requireNotNull(model.properties)

    NimbusSoftwareLayer(condition = row.shouldDisableHardwareAcceleration()) {
        FlowRow(
            modifier = modifier.box(row)
        ) {
            content()
        }
    }
}


