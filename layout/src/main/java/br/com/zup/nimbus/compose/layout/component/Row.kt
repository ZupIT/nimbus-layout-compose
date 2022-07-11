package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.container
import br.com.zup.nimbus.compose.layout.extensions.rowParentStretch
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ParentContainer
import br.com.zup.nimbus.compose.layout.model.RowModel
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

private object NimbusRowScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as RowScope }
}

@Composable
internal fun Row(
    model: RowModel,
    modifier: Modifier = Modifier,
    parentComponentName: String? = null,
    content: Component,
) {
    val mainAxisAlignment = requireNotNull(model.mainAxisAlignment)
    val crossAxisAlignment = requireNotNull(model.crossAxisAlignment)
    val horizontalArrangement = mainAxisAlignment.toHorizontalArrangement()
    val verticalAlignment = crossAxisAlignment.toVerticalAlignment()
    NimbusSoftwareLayer(condition = model.shouldDisableHardwareAcceleration()) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            modifier = modifier
                .rowParentStretch(model)
                .container(model, parentComponentName, NimbusRowScope.instance)
        ) {
            content()
        }
    }
}


