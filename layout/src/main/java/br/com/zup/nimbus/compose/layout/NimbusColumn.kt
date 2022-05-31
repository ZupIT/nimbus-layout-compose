package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.LayoutComponent
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

private object NimbusColumnScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

@Composable
internal fun NimbusColumn(
    model: ColumnModel,
    parentLayout: LayoutComponent? = null,
    modifier: Modifier = Modifier,
    content: Component,
) {
    Column(
        modifier = modifier.container(container = model,
            parentLayout = parentLayout,
            scope = NimbusColumnScope.instance)
    ) {
        content()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ColumnModel : Container()
