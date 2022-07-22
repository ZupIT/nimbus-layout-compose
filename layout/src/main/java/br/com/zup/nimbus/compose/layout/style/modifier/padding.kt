package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.Padding

internal fun Modifier.paddingStyle(style: Padding): Modifier {
    var paddingStart: Double? = null
    var paddingEnd: Double? = null
    var paddingTop: Double? = null
    var paddingBottom: Double? = null

    style.padding?.let {
        paddingStart = it
        paddingEnd = it
        paddingTop = it
        paddingBottom = it
    }

    style.paddingStart?.let {
        paddingStart = it
    }

    style.paddingEnd?.let {
        paddingEnd = it
    }

    style.paddingTop?.let {
        paddingTop = it
    }

    style.paddingBottom?.let {
        paddingBottom = it
    }

    style.paddingHorizontal?.let {
        paddingStart = it
        paddingEnd = it
    }

    style.paddingVertical?.let {
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
