package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.accessibility
import br.com.zup.nimbus.compose.layout.model.Action
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.TouchableModel

@Composable
internal fun Touchable(
    model: TouchableModel,
    modifier: Modifier = Modifier,
    content: Component,
) {
    Column(
        modifier = modifier
            .clickable {
                model.onPress(null)
            }
            .accessibility(model.accessibility)
    ) {
        content()
    }
}


