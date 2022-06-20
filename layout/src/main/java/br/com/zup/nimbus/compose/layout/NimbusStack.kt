package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.box
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.NimbusStackApi
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

@Composable
internal fun NimbusStack(
    model: NimbusStackApi,
    modifier: Modifier = Modifier,
    content: Component,
) {
    val stack = requireNotNull(model.properties)

    NimbusSoftwareLayer(condition = stack.shouldDisableHardwareAcceleration()) {
        Box(
            modifier = modifier.box(stack)
        ) {
            content()
        }
    }

}
