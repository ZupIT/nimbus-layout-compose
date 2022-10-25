package br.com.zup.nimbus.compose.layout.component.container

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.component.container.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.component.container.MainAxisAlignment
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import com.zup.nimbus.processor.annotation.AutoDeserialize
import com.zup.nimbus.processor.annotation.Root

@Composable
@AutoDeserialize
internal fun Column(
    @Root style: Box,
    crossAxisAlignment: CrossAxisAlignment?,
    mainAxisAlignment: MainAxisAlignment?,
    content: @Composable () -> Unit,
) {
    val verticalArrangement = (mainAxisAlignment ?: MainAxisAlignment.Start).toVerticalArrangement()
    val horizontalAlignment = (crossAxisAlignment ?: CrossAxisAlignment.Start).toHorizontalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = Modifier.boxStyle(style)
        ) {
            content()
        }
    }
}
