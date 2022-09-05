package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.deserialization.BoxDeserializer
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import com.zup.nimbus.processor.Computed
import com.zup.nimbus.processor.Root
import com.zup.nimbus.processor.ServerDrivenComponent

@Composable
@ServerDrivenComponent
internal fun Stack(
    @Computed<BoxDeserializer>(BoxDeserializer::class) style: Box,
    content: @Composable () -> Unit,
) {
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Box(modifier = Modifier.boxStyle(style)) {
            content()
        }
    }
}
