package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.Margin

internal fun Modifier.marginStyle(style: Margin): Modifier {
    var paddingStart: Double? = null
    var paddingEnd: Double? = null
    var paddingTop: Double? = null
    var paddingBottom: Double? = null

    style.margin?.let {
        paddingStart = it
        paddingEnd = it
        paddingTop = it
        paddingBottom = it
    }

    style.marginStart?.let {
        paddingStart = it
    }

    style.marginEnd?.let {
        paddingEnd = it
    }

    style.marginTop?.let {
        paddingTop = it
    }

    style.marginBottom?.let {
        paddingBottom = it
    }

    style.marginHorizontal?.let {
        paddingStart = it
        paddingEnd = it
    }

    style.marginVertical?.let {
        paddingTop = it
        paddingBottom = it
    }

    return this.padding(
        start = paddingStart?.dp ?: 0.dp,
        end = paddingEnd?.dp ?: 0.dp,
        top = paddingTop?.dp ?: 0.dp,
        bottom = paddingBottom?.dp ?: 0.dp,
    )
}
