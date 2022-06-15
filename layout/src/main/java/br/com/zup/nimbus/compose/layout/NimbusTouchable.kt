package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.accessibility
import br.com.zup.nimbus.compose.layout.model.WithAccessibility
import br.com.zup.nimbus.compose.layout.model.Action
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.TouchableApi

@Composable
internal fun NimbusTouchable(
    modifier: Modifier = Modifier,
    model: TouchableApi,
    onPress: Action,
    content: Component,
) {
    Box(
        modifier = modifier
            .clickable {
                onPress(null)
            }
            .accessibility(model = model.properties)
    ) {
        content()
    }
}

internal data class TouchableModel(
    val onPress: Action,
    val children: Component,
    val accessibility: WithAccessibility? = null,
)
