package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.AbstractComponentApi
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.model.ParentContainer

internal fun Modifier.container(
    container: Container,
    parentComponent: AbstractComponentApi<ParentContainer>? = null,
    @LayoutScopeMarker
    scope: Any? = null,
    modifier: Modifier = Modifier,
) = this.then(
    modifier
        .applyScopeModifier(scope, container)
        .margin(container)
        .size(container)
        .fillMaxSize(container, parentComponent)
        .shadow(container)
        .border(container)
        .background(container.backgroundColor)
        .padding(container)
)



internal fun Modifier.applyScopeModifier(
    scope: Any? = null,
    container: Container,
    modifier: Modifier = Modifier
) = this.then(with(container) {
    if(scope == null) return@with modifier
    var newModifier = modifier
    when (scope) {
        is RowScope -> {
            with(scope)
            {
                container.flex?.let {
                    newModifier = newModifier.weight(it.toFloat())
                }

                container.crossAxisAlignment?.let { crossAxis ->
                    if (crossAxis == CrossAxisAlignment.STRETCH) {
                        newModifier = newModifier.height(IntrinsicSize.Min)
                    }
                }
            }
        }
        is ColumnScope -> {
            with(scope) {
                container.flex?.let {
                    newModifier = newModifier.weight(it.toFloat())
                }

                container.crossAxisAlignment?.let { crossAxis ->
                    if (crossAxis == CrossAxisAlignment.STRETCH) {
                        newModifier = newModifier.width(IntrinsicSize.Min)
                    }
                }
            }
        }
        else -> {}
    }
    return@with newModifier
})
