package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root

@Composable
@AutoDeserialize
internal fun Stack(
    @Root style: Box,
    content: @Composable () -> Unit,
) {
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Box(modifier = Modifier.boxStyle(style)) {
            content()
        }
    }
}
