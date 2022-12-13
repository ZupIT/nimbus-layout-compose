package br.com.zup.nimbus.compose.layout.component.container

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Root
import br.com.zup.nimbus.compose.layout.component.NimbusSoftwareLayer
import br.com.zup.nimbus.compose.layout.style.model.Box
import br.com.zup.nimbus.compose.layout.style.modifier.boxStyle
import br.com.zup.nimbus.compose.deserialization.DeserializationContext
import br.com.zup.nimbus.compose.layout.style.model.Padding

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
    val content = remember(context.component?.node?.children?.map { it.id }) {
        context.component?.childrenAsList?.invoke() ?: emptyList()
    }
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        LazyColumn(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            contentPadding = style.padding.toPaddingValues(),
            modifier = Modifier.boxStyle(style.copy(omitPadding = true)),
        ) {
            items(items = content) { item: @Composable () -> Unit ->
                item()
            }
        }
    }
}
