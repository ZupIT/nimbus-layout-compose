package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.model.ComponentNames
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.Size

internal fun Modifier.applyChildStretch(
    container: Container,
    parentComponentName: String?,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        if (container.stretch.isTrue()) {
            if (parentComponentName == ComponentNames.NIMBUS_ROW) {
                newModifier = newModifier.fillMaxHeight()
            } else if (parentComponentName == ComponentNames.NIMBUS_COLUMN) {
                newModifier = newModifier.fillMaxWidth()
            }
        }

        return@with newModifier
    }
)

internal fun Modifier.size(
    container: Size,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        newModifier = newModifier.clipped(container.clipped)

        container.width?.let {
            newModifier = newModifier.width(it.dp)
        }

        container.height?.let {
            newModifier = newModifier.height(it.dp)
        }

        container.minHeight?.let {
            newModifier = newModifier.heightIn(min = it.dp)
        }

        container.maxHeight?.let {
            newModifier = newModifier.heightIn(max = it.dp)
        }

        container.minWidth?.let {
            newModifier = newModifier.heightIn(min = it.dp)
        }

        container.maxWidth?.let {
            newModifier = newModifier.heightIn(max = it.dp)
        }

        return@with newModifier
    }
)
