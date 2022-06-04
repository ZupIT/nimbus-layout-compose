package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.com.zup.nimbus.compose.layout.model.ComponentNames
import br.com.zup.nimbus.compose.layout.model.ComponentStructure
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.CrossAxisAlignment

internal fun Modifier.accessibility(accessibility: Accessibility?) = this.then(
    accessibility?.let { a ->
        semantics(mergeDescendants = true) {
            a.label?.let {
                contentDescription = it
            }
            if (a.isHeader == true) {
                heading()
            }
        }
    } ?: this
)

internal fun Modifier.background(color: String?) = this.then(
    color?.let { Modifier.background(it.color) } ?: this
)

internal fun Modifier.container(
    container: Container,
    parentComponent: ComponentStructure? = null,
    @LayoutScopeMarker
    scope: Any,
) = this.then(
        this.applyScopeModifier(scope, container)
            .margin(container)
            .size(container)
            .fillMaxSize(container, parentComponent)
            .background(container.backgroundColor)
            .padding(container)
)

internal fun Modifier.applyScopeModifier(
    scope: Any,
    container: Container,
    modifier: Modifier = Modifier
) = this.then(with(container) {
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

internal fun Modifier.fillMaxSize(
    container: Container,
    parentComponent: ComponentStructure?,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        if (parentComponent != null) {
            if (parentComponent.component == ComponentNames.NIMBUS_ROW) {
                if (container.height == null) {
                    parentComponent.properties?.crossAxisAlignment?.let { crossAxis ->
                        if (crossAxis == CrossAxisAlignment.STRETCH) {
                            newModifier = newModifier.fillMaxHeight()
                        }
                    }
                }
            } else if (parentComponent.component == ComponentNames.NIMBUS_COLUMN) {
                if (container.width == null) {
                    parentComponent.properties?.crossAxisAlignment?.let { crossAxis ->
                        if (crossAxis == CrossAxisAlignment.STRETCH) {
                            newModifier = newModifier.fillMaxWidth()
                        }
                    }
                }
            }
        }

        return@with newModifier
    }
)

internal fun Modifier.size(
    container: Container,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        container.width?.let {
            newModifier = newModifier.width(it.dp)
        }

        container.height?.let {
            newModifier = newModifier.height(it.dp)
        }

        return@with newModifier
    }
)

internal fun Modifier.margin(
    container: Container,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        container.margin?.let {
            newModifier = newModifier.padding(it.dp)
        }

        container.marginStart?.let {
            newModifier = newModifier.padding(start = it.dp)
        }

        container.marginEnd?.let {
            newModifier = newModifier.padding(end = it.dp)
        }

        container.marginTop?.let {
            newModifier = newModifier.padding(top = it.dp)
        }

        container.marginBottom?.let {
            newModifier = newModifier.padding(bottom = it.dp)
        }

        container.marginHorizontal?.let {
            newModifier = newModifier.padding(start = it.dp)
            newModifier = newModifier.padding(end = it.dp)
        }

        container.marginVertical?.let {
            newModifier = newModifier.padding(top = it.dp)
            newModifier = newModifier.padding(bottom = it.dp)
        }
        return@with newModifier
    }
)

internal fun Modifier.padding(
    container: Container,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        container.padding?.let {
            newModifier = newModifier.padding(it.dp)
        }

        container.paddingStart?.let {
            newModifier = newModifier.padding(start = it.dp)
        }

        container.paddingEnd?.let {
            newModifier = newModifier.padding(end = it.dp)
        }

        container.paddingTop?.let {
            newModifier = newModifier.padding(top = it.dp)
        }

        container.paddingBottom?.let {
            newModifier = newModifier.padding(bottom = it.dp)
        }

        container.paddingHorizontal?.let {
            newModifier = newModifier.padding(start = it.dp)
            newModifier = newModifier.padding(end = it.dp)
        }

        container.paddingVertical?.let {
            newModifier = newModifier.padding(top = it.dp)
            newModifier = newModifier.padding(bottom = it.dp)
        }

        return@with newModifier
    }
)

val String.color
    get() = Color(android.graphics.Color.parseColor(this))
