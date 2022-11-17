package br.com.zup.nimbus.compose.layout.component.container

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root

@Composable
@AutoDeserialize
internal fun Row(
    @Root style: Box,
    crossAxisAlignment: CrossAxisAlignment?,
    mainAxisAlignment: MainAxisAlignment?,
    content: @Composable () -> Unit,
) {
    val horizontalArrangement = (mainAxisAlignment ?: MainAxisAlignment.Start).toHorizontalArrangement()
    val verticalAlignment = (crossAxisAlignment ?: CrossAxisAlignment.Start).toVerticalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            modifier = Modifier.boxStyle(style)
        ) {
            content()
        }
    }
}
