package br.com.zup.nimbus.compose.layout.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import com.google.accompanist.flowlayout.FlowColumn
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root

@Composable
@AutoDeserialize
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


