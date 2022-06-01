package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ComponentStructure

private object NimbusColumnScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

@Composable
internal fun NimbusColumn(
    model: ComponentStructure,
    parentComponent: ComponentStructure? = null,
    modifier: Modifier = Modifier,
    content: Component,
) {
    Column(
        modifier = modifier.container(container = model.properties!!,
            parentComponent = parentComponent,
            scope = NimbusColumnScope.instance)
    ) {
        content()
    }
}
