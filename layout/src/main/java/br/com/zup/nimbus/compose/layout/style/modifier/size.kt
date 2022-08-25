package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.Size

internal fun Modifier.sizeStyle(style: Size): Modifier {
    var current = this

    style.width?.let {
        current = current.width(it.dp)
    }

    style.height?.let {
        current = current.height(it.dp)
    }

    style.minHeight?.let {
        current = current.heightIn(min = it.dp)
    }

    style.maxHeight?.let {
        current = current.heightIn(max = it.dp)
    }

    style.minWidth?.let {
        current = current.heightIn(min = it.dp)
    }

    style.maxWidth?.let {
        current = current.heightIn(max = it.dp)
    }

    if (style.clipped == true) current = current.clipToBounds()

    return current
}
