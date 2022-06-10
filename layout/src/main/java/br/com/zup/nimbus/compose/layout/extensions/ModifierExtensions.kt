package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import br.com.zup.nimbus.compose.layout.ColorUtils
import br.com.zup.nimbus.compose.layout.model.Accessibility

internal fun Modifier.accessibility(accessibility: Accessibility?, modifier: Modifier = Modifier) = this.then(
    accessibility?.let { a ->
        modifier.semantics(mergeDescendants = true) {
            a.label?.let {
                contentDescription = it
            }
            if (a.isHeader == true) {
                heading()
            }
        }
    } ?: modifier
)

internal fun Modifier.background(color: String?, modifier: Modifier = Modifier) = this.then(
    color?.let { modifier.background(it.color) } ?: modifier
)

internal fun Modifier.clipped(clipped: Boolean?, modifier: Modifier = Modifier) = this.then(
    clipped?.let { if(clipped) modifier.clipToBounds() else modifier } ?: modifier
)

val String.color
    get() = Color(ColorUtils.hexColor(this))
