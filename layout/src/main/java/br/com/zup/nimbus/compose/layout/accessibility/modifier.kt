package br.com.zup.nimbus.compose.layout.accessibility

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import br.com.zup.nimbus.compose.layout.extensions.isTrue

internal fun Modifier.accessibility(
    accessibility: Accessibility?,
    modifier: Modifier = Modifier,
) = this.then(
    accessibility?.label?.let { description ->
        modifier.semantics(mergeDescendants = true) {
            contentDescription = description
            if (accessibility.isHeader.isTrue()) {
                heading()
            }
        }
    } ?: modifier
)
