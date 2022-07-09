package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.columnParentStretch
import br.com.zup.nimbus.compose.layout.extensions.container
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ColumnModel
import br.com.zup.nimbus.compose.layout.model.ParentContainer
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

private object NimbusColumnScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

@Composable
internal fun Column(
    model: ColumnModel,
    modifier: Modifier = Modifier,
    parentComponentName: String? = null,
    content: Component,
) {
    val mainAxisAlignment = requireNotNull(model.mainAxisAlignment)
    val crossAxisAlignment = requireNotNull(model.crossAxisAlignment)
    val verticalArrangement = mainAxisAlignment.toVerticalArrangement()
    val horizontalAlignment = crossAxisAlignment.toHorizontalAlignment()
    NimbusSoftwareLayer(condition = model.shouldDisableHardwareAcceleration()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = modifier
                .columnParentStretch(model)
                .container(model, parentComponentName, NimbusColumnScope.instance)
        ) {
            content()
        }
    }
}
