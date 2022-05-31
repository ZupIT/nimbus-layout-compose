package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.LayoutComponent

private object NimbusRowScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as RowScope }
}

@Composable
internal fun NimbusRow(
    container: Container,
    parentLayout: LayoutComponent? = null,
    modifier: Modifier = Modifier,
    content: Component,
) {
    Row(
        modifier = modifier.container(container = container,
            parentLayout = parentLayout,
            scope = NimbusRowScope.instance)
    ) {
        content()
    }
}

