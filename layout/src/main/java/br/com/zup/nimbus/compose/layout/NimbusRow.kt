package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ComponentStructure
import br.com.zup.nimbus.compose.layout.model.GenericComponentApi

private object NimbusRowScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as RowScope }
}

@Composable
internal fun NimbusRow(
    model: ComponentStructure,
    modifier: Modifier = Modifier,
    parentComponent: GenericComponentApi? = null,
    content: Component,
) {
    val container = requireNotNull(model.properties)
    val horizontalArrangement = container.mainAxisAlignment?.toHorizontalArrangement()
    val verticalAlignment = container.crossAxisAlignment?.toVerticalAlignment()

    Row(
        horizontalArrangement = horizontalArrangement ?: Arrangement.Start,
        verticalAlignment = verticalAlignment ?: Alignment.Top,
        modifier = modifier.container(container, parentComponent, NimbusRowScope.instance)
    ) {
        content()
    }
}


