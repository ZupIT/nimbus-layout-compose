package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.zup.nimbus.compose.layout.style.model.Container
import br.com.zup.nimbus.compose.layout.style.model.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.style.model.MainAxisAlignment
import br.com.zup.nimbus.compose.layout.style.modifier.containerStyle
import br.zup.com.nimbus.compose.ComponentData
import br.zup.com.nimbus.compose.NimbusMode
import br.zup.com.nimbus.compose.NimbusTheme
import com.zup.nimbus.core.deserialization.ComponentDeserializer

@Composable
fun NimbusLazyRow(data: ComponentData) {
    val nimbus = NimbusTheme.nimbus
    val properties = remember { ComponentDeserializer(logger = nimbus.logger, node = data.node) }
    properties.start()
    val style = NimbusEntityDeserializer.deserialize(properties, data, Container::class)
    val mainAxisAlignment = style.mainAxisAlignment ?: MainAxisAlignment.Start
    val crossAxisAlignment = style.crossAxisAlignment ?: CrossAxisAlignment.Start
    val horizontalArrangement = mainAxisAlignment.toHorizontalArrangement()
    val verticalAlignment = crossAxisAlignment.toVerticalAlignment()

    val content = data.childrenAsList
    val isSuccessful = properties.end()
    if (isSuccessful) {
        NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
            LazyRow(
                horizontalArrangement = horizontalArrangement,
                verticalAlignment = verticalAlignment,
                modifier = Modifier.containerStyle(style)) {
                items(items = content) { item: @Composable () -> Unit ->
                    item()
                }
            }
        }
    } else if (nimbus.mode == NimbusMode.Development) {
        Text("""Error while deserializing ${data.node.component}.""", color = Color.Red)
    }

}
