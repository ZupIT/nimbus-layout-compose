package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.accessibility
import br.com.zup.nimbus.compose.layout.model.Action
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.TouchableApi
import br.com.zup.nimbus.compose.layout.model.WithAccessibility

@Composable
internal fun NimbusTouchable(
    model: TouchableApi,
    modifier: Modifier = Modifier,
    onPress: Action,
    content: Component,
) {
    val touchable = requireNotNull(model.properties)

    Column(
        modifier = modifier
            .clickable {
                onPress(null)
            }
            .accessibility(model = touchable)
    ) {
        content()
    }
}

internal data class TouchableModel(
    val onPress: Action,
    val children: Component,
    val accessibility: WithAccessibility? = null,
)
