package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.model.Padding

internal fun Modifier.padding(
    container: Padding,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier
        var paddingStart: Double? = null
        var paddingEnd: Double? = null
        var paddingTop: Double? = null
        var paddingBottom: Double? = null

        container.padding?.let {
            paddingStart = it
            paddingEnd = it
            paddingTop = it
            paddingBottom = it
        }

        container.paddingStart?.let {
            paddingStart = it
        }

        container.paddingEnd?.let {
            paddingEnd = it
        }

        container.paddingTop?.let {
            paddingTop = it
        }

        container.paddingBottom?.let {
            paddingBottom = it
        }

        container.paddingHorizontal?.let {
            paddingStart = it
            paddingEnd = it
        }

        container.paddingVertical?.let {
            paddingTop = it
            paddingBottom = it
        }

        newModifier = padding(modifier = newModifier,
            paddingStart = paddingStart,
            paddingEnd = paddingEnd,
            paddingTop = paddingTop,
            paddingBottom = paddingBottom)

        return@with newModifier
    }
)

internal fun padding(
    modifier: Modifier = Modifier,
    paddingStart: Double?,
    paddingEnd: Double?,
    paddingTop: Double?,
    paddingBottom: Double?,
): Modifier {
    var newModifier = modifier
    paddingStart?.let {
        newModifier = newModifier.padding(start = it.dp)
    }

    paddingEnd?.let {
        newModifier = newModifier.padding(end = it.dp)
    }

    paddingTop?.let {
        newModifier = newModifier.padding(top = it.dp)
    }

    paddingBottom?.let {
        newModifier = newModifier.padding(bottom = it.dp)
    }
    return newModifier
}
