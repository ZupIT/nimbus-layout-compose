package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.LayoutComponent

private object NimbusColumnScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

@Composable
internal fun NimbusColumn(
    container: Container,
    parentLayout: LayoutComponent? = null,
    modifier: Modifier = Modifier,
    content: Component,
) {
    Column(
        modifier = modifier.container(container = container,
            parentLayout = parentLayout,
            scope = NimbusColumnScope.instance)
    ) {
        content()
    }
}
