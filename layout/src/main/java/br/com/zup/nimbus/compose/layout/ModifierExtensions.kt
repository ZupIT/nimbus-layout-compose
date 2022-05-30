package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.com.zup.nimbus.compose.layout.model.Container

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
    container: Container? = null,
    @LayoutScopeMarker
    scope: Any? = null) = this.then(

    container?.let { c ->
        var modifier = this
        c.backgroundColor?.let {
            modifier = modifier.background(it.color)
        }

        container.flex?.let { flex ->
            when(scope) {
                is RowScope -> {
                    with(scope) {
                        modifier = modifier.weight(flex.toFloat())
                    }
                }
                is ColumnScope -> {
                    with(scope) {
                        modifier = modifier.weight(flex.toFloat())
                    }
                }
            }
        }

        container.height?.let {
            modifier = modifier.height(it.dp)
        }

        container.width?.let {
            modifier = modifier.width(it.dp)
        }


        //TODO add other properties

        return@let modifier
    } ?: this
)

val String.color
    get() = Color(android.graphics.Color.parseColor(this))
