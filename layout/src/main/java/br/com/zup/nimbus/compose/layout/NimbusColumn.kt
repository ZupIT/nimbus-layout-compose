package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.container
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ComponentStructure
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

private object NimbusColumnScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

@Composable
internal fun NimbusColumn(
    model: ComponentStructure,
    modifier: Modifier = Modifier,
    parentComponent: ComponentStructure? = null,
    content: Component,
) {
    val container = requireNotNull(model.properties)
    val mainAxisAlignment = requireNotNull(container.mainAxisAlignment)
    val crossAxisAlignment = requireNotNull(container.crossAxisAlignment)
    val verticalArrangement = mainAxisAlignment.toVerticalArrangement()
    val horizontalAlignment = crossAxisAlignment.toHorizontalAlignment()
    NimbusSoftwareLayer(condition = container.shouldDisableHardwareAcceleration()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = modifier.container(container, parentComponent, NimbusColumnScope.instance)
        ) {
            content()
        }
    }
}
