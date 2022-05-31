package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.LayoutComponent
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

private object NimbusRowScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as RowScope }
}

@Composable
internal fun NimbusRow(
    model: RowModel,
    modelLayout: LayoutComponent? = null,
    modifier: Modifier = Modifier,
    content: Component,
) {
    Row(
        modifier = modifier.container(container = model,
            parentLayout = modelLayout,
            scope = NimbusRowScope.instance)
    ) {
        content()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
internal class RowModel : Container()
