package br.com.zup.nimbus.compose.layout

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics

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
