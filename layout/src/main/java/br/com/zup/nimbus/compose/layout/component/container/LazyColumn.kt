package br.com.zup.nimbus.compose.layout.component.container

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import br.zup.com.nimbus.compose.deserialization.DeserializationContext

@Composable
@AutoDeserialize
internal fun LazyColumn(
    @Root style: Box,
    crossAxisAlignment: CrossAxisAlignment?,
    mainAxisAlignment: MainAxisAlignment?,
    context: DeserializationContext,
) {
    val verticalArrangement = (mainAxisAlignment ?: MainAxisAlignment.Start).toVerticalArrangement()
    val horizontalAlignment = (crossAxisAlignment ?: CrossAxisAlignment.Start).toHorizontalAlignment()
    val content = context.component?.childrenAsList ?: emptyList()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        LazyColumn(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = Modifier.boxStyle(style),
        ) {
            items(items = content) { item: @Composable () -> Unit ->
                item()
            }
        }
    }
}
