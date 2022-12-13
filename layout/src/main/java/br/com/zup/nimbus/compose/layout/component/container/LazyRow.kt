package br.com.zup.nimbus.compose.layout.component.container

import androidx.compose.foundation.lazy.LazyRow
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

@Composable
@AutoDeserialize
internal fun LazyRow(
    @Root style: Box,
    crossAxisAlignment: CrossAxisAlignment?,
    mainAxisAlignment: MainAxisAlignment?,
    context: DeserializationContext,
) {
    val horizontalArrangement = (mainAxisAlignment ?: MainAxisAlignment.Start).toHorizontalArrangement()
    val verticalAlignment = (crossAxisAlignment ?: CrossAxisAlignment.Start).toVerticalAlignment()
    val content = remember(context.component?.node?.children?.map { it.id }) {
        context.component?.childrenAsList?.invoke() ?: emptyList()
    }
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        LazyRow(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            contentPadding = style.padding.toPaddingValues(),
            modifier = Modifier.boxStyle(style.copy(omitPadding = true)),
        ) {
            items(items = content) { item: @Composable () -> Unit ->
                item()
            }
        }
    }
}
