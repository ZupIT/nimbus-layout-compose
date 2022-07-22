package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.isTrue
import br.com.zup.nimbus.compose.layout.style.model.Container
import br.com.zup.nimbus.compose.layout.ComponentNames
import br.com.zup.nimbus.compose.layout.getFullNameOfComponent

internal fun Modifier.containerStyle(
    style: Container,
    parentComponentName: String? = null,
    @LayoutScopeMarker
    scope: Any? = null,
) = this.then(
    applyScopeModifier(scope, style)
    .marginStyle(style)
    .sizeStyle(style)
    .applyChildStretch(style, parentComponentName)
    .shadowStyle(style)
    .borderStyle(style)
    .background(style.backgroundColor)
    .paddingStyle(style)
)

internal fun Modifier.applyChildStretch(
    style: Container,
    parentComponentName: String?,
    modifier: Modifier = Modifier
) = this.then(
    with(style) {
        var newModifier = modifier

        if (style.stretch.isTrue()) {
            if (parentComponentName == getFullNameOfComponent(ComponentNames.ROW)) {
                newModifier = newModifier.fillMaxHeight()
            } else if (parentComponentName == getFullNameOfComponent(ComponentNames.COLUMN)) {
                newModifier = newModifier.fillMaxWidth()
            }
        }

        return@with newModifier
    }
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
            }
        }
        is ColumnScope -> {
            with(scope) {
                container.flex?.let {
                    newModifier = newModifier.weight(it.toFloat())
                }
            }
        }
        else -> {}
    }
    return@with newModifier
})
