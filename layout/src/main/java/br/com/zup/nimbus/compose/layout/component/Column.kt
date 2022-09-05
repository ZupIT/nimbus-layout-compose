package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.deserialization.ContainerDeserializer
import br.com.zup.nimbus.compose.layout.style.model.Container
import br.com.zup.nimbus.compose.layout.style.model.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.style.model.MainAxisAlignment
import br.com.zup.nimbus.compose.layout.style.modifier.containerStyle
import com.zup.nimbus.processor.Computed
import com.zup.nimbus.processor.ParentName
import com.zup.nimbus.processor.ServerDrivenComponent



@Composable
@ServerDrivenComponent
internal fun Column(
    @Computed<ContainerDeserializer>(ContainerDeserializer::class) style: Container,
    @ParentName parentComponentName: String? = null,
    content: @Composable () -> Unit,
) {
    val mainAxisAlignment = style.mainAxisAlignment ?: MainAxisAlignment.Start
    val crossAxisAlignment = style.crossAxisAlignment ?: CrossAxisAlignment.Start
    val verticalArrangement = mainAxisAlignment.toVerticalArrangement()
    val horizontalAlignment = crossAxisAlignment.toHorizontalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = Modifier.containerStyle(style, parentComponentName)
        ) {
            content()
        }
    }
}
