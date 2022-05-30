package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.com.zup.nimbus.compose.layout.model.Action
import br.com.zup.nimbus.compose.layout.model.Component

@Composable
internal fun NimbusTouchable(
    model: TouchableModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable {
                model.onPress(null)
            }
            .accessibility(model.accessibility)
    ) {
        model.children()
    }
}

internal data class TouchableModel(
    val onPress: Action,
    val children: Component,
    val accessibility: Accessibility? = null)
