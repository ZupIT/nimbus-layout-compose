package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.container
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.NimbusRowApi
import br.com.zup.nimbus.compose.layout.model.ParentContainerApi
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

private object NimbusRowScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as RowScope }
}

@Composable
internal fun NimbusRow(
    model: NimbusRowApi,
    modifier: Modifier = Modifier,
    parentComponent: ParentContainerApi? = null,
    content: Component,
) {
    val container = requireNotNull(model.properties)
    val mainAxisAlignment = requireNotNull(container.mainAxisAlignment)
    val crossAxisAlignment = requireNotNull(container.crossAxisAlignment)
    val horizontalArrangement = mainAxisAlignment.toHorizontalArrangement()
    val verticalAlignment = crossAxisAlignment.toVerticalAlignment()
    NimbusSoftwareLayer(condition = container.shouldDisableHardwareAcceleration()) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            modifier = modifier.container(container, parentComponent, NimbusRowScope.instance)
        ) {
            content()
        }
    }
}


