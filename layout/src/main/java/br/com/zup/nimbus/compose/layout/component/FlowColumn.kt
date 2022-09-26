package br.com.zup.nimbus.compose.layout.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import com.google.accompanist.flowlayout.FlowColumn
import com.zup.nimbus.processor.Root
import com.zup.nimbus.processor.ServerDrivenComponent

@Composable
@ServerDrivenComponent
internal fun FlowColumn(
    @Root style: Box,
    content: @Composable () -> Unit,
) {
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        FlowColumn(modifier = Modifier.boxStyle(style)) {
            content()
        }
    }
}


