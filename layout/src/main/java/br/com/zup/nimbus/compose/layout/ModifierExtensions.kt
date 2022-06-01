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

internal fun Modifier.container(
    container: Container,
    parentComponent: ComponentStructure? = null,
    @LayoutScopeMarker
    scope: Any? = null,
) = this.then(

    with(container) {
        var modifier = this@container

        //Should apply margin before background
        modifier = modifier.margin(this)

        container.backgroundColor?.let {
            modifier = modifier.background(it.color)
        }

        modifier = modifier.applyScopeModifier(scope, container)

        modifier = modifier.fillMaxSize(container, parentComponent)

        modifier = modifier.size(container)

        //Padding should be after background
        modifier = modifier.padding(this)
        //TODO add other properties

        return@with modifier
    }
)

internal fun Modifier.applyScopeModifier(
    scope: Any?,
    container: Container,
) = this.then(with(container) {
    var modifier = this@applyScopeModifier
    when (scope) {
        is RowScope -> {
            with(scope)
            {
                container.flex?.let {
                    modifier = modifier.weight(it.toFloat())
                }

                container.crossAxisAlignment?.let { crossAxis ->
                    if (crossAxis == CrossAxisAlignment.STRETCH) {
                        modifier = modifier.height(IntrinsicSize.Min)
                    }
                }
            }
        }
        is ColumnScope -> {
            with(scope) {
                container.flex?.let {
                    modifier = modifier.weight(it.toFloat())
                }

                container.crossAxisAlignment?.let { crossAxis ->
                    if (crossAxis == CrossAxisAlignment.STRETCH) {
                        modifier = modifier.width(IntrinsicSize.Min)
                    }
                }
            }
        }
        else -> {}
    }
    return@with modifier
})

internal fun Modifier.fillMaxSize(
    container: Container,
    parentComponent: ComponentStructure?,
) = this.then(
    with(container) {
        var modifier = this@fillMaxSize

        if (parentComponent != null) {
            if (parentComponent.component == ComponentNames.NIMBUS_ROW) {
                if (container.height == null) {
                    parentComponent.properties?.crossAxisAlignment?.let { crossAxis ->
                        if (crossAxis == CrossAxisAlignment.STRETCH) {
                            modifier = modifier.fillMaxHeight()
                        }
                    }
                }
            } else if (parentComponent.component == ComponentNames.NIMBUS_COLUMN) {
                if (container.width == null) {
                    parentComponent.properties?.crossAxisAlignment?.let { crossAxis ->
                        if (crossAxis == CrossAxisAlignment.STRETCH) {
                            modifier = modifier.fillMaxWidth()
                        }
                    }
                }
            }
        }

        return@with modifier
    }
)

internal fun Modifier.size(
    container: Container,
) = this.then(
    with(container) {
        var modifier = this@size

        container.width?.let {
            modifier = modifier.width(it.dp)
        }

        container.height?.let {
            modifier = modifier.height(it.dp)
        }

        return@with modifier
    }
)

internal fun Modifier.margin(
    container: Container,
) = this.then(
    with(container) {
        var modifier = this@margin

        container.margin?.let {
            modifier = modifier.padding(it.dp)
        }

        container.marginStart?.let {
            modifier = modifier.padding(start = it.dp)
        }

        container.marginEnd?.let {
            modifier = modifier.padding(end = it.dp)
        }

        container.marginTop?.let {
            modifier = modifier.padding(top = it.dp)
        }

        container.marginBottom?.let {
            modifier = modifier.padding(bottom = it.dp)
        }

        container.marginHorizontal?.let {
            modifier = modifier.padding(start = it.dp)
            modifier = modifier.padding(end = it.dp)
        }

        container.marginVertical?.let {
            modifier = modifier.padding(top = it.dp)
            modifier = modifier.padding(bottom = it.dp)
        }
        return@with modifier
    }
)

internal fun Modifier.padding(
    container: Container,
) = this.then(
    with(container) {
        var modifier = this@padding

        container.padding?.let {
            modifier = modifier.padding(it.dp)
        }

        container.paddingStart?.let {
            modifier = modifier.padding(start = it.dp)
        }

        container.paddingEnd?.let {
            modifier = modifier.padding(end = it.dp)
        }

        container.paddingTop?.let {
            modifier = modifier.padding(top = it.dp)
        }

        container.paddingBottom?.let {
            modifier = modifier.padding(bottom = it.dp)
        }

        container.paddingHorizontal?.let {
            modifier = modifier.padding(start = it.dp)
            modifier = modifier.padding(end = it.dp)
        }

        container.paddingVertical?.let {
            modifier = modifier.padding(top = it.dp)
            modifier = modifier.padding(bottom = it.dp)
        }

        return@with modifier
    }
)

val String.color
    get() = Color(android.graphics.Color.parseColor(this))
