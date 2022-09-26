package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Container
import br.com.zup.nimbus.compose.layout.style.model.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.style.model.MainAxisAlignment
import br.com.zup.nimbus.compose.layout.style.modifier.containerStyle
import com.zup.nimbus.processor.ParentName
import com.zup.nimbus.processor.Root
import com.zup.nimbus.processor.ServerDrivenComponent

@Composable
@ServerDrivenComponent
internal fun Row(
    @Root style: Container,
    @ParentName parentComponentName: String? = null,
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
            modifier = Modifier.containerStyle(style, parentComponentName)
        ) {
            content()
        }
    }
}
