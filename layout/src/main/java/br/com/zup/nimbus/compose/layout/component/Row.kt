package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Container
import br.com.zup.nimbus.compose.layout.style.model.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.style.model.MainAxisAlignment
import br.com.zup.nimbus.compose.layout.style.modifier.containerStyle
import com.zup.nimbus.processor.annotation.AutoDeserialize
import com.zup.nimbus.processor.annotation.Root

@Composable
@AutoDeserialize
internal fun Row(
    @Root style: Container,
    content: @Composable () -> Unit,
) {
    val mainAxisAlignment = style.mainAxisAlignment ?: MainAxisAlignment.Start
    val crossAxisAlignment = style.crossAxisAlignment ?: CrossAxisAlignment.Start
    val horizontalArrangement = mainAxisAlignment.toHorizontalArrangement()
    val verticalAlignment = crossAxisAlignment.toVerticalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            modifier = Modifier.containerStyle(style)
        ) {
            content()
        }
    }
}
