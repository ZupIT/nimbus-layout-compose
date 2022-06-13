package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Margin

internal fun Modifier.margin(
    container: Margin,
    modifier: Modifier = Modifier
) = this.then(
    with(container) {
        var newModifier = modifier

        var paddingStart: Double? = null
        var paddingEnd: Double? = null
        var paddingTop: Double? = null
        var paddingBottom: Double? = null

        container.margin?.let {
            paddingStart = it
            paddingEnd = it
            paddingTop = it
            paddingBottom = it
        }

        container.marginStart?.let {
            paddingStart = it
        }

        container.marginEnd?.let {
            paddingEnd = it
        }

        container.marginTop?.let {
            paddingTop = it
        }

        container.marginBottom?.let {
            paddingBottom = it
        }

        container.marginHorizontal?.let {
            paddingStart = it
            paddingEnd = it
        }

        container.marginVertical?.let {
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
