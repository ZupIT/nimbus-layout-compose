package br.com.zup.nimbus.compose.layout.component.scroll

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier

internal fun Modifier.scroll(
    direction: ScrollViewDirection,
    scrollIndicator: Boolean,
    verticalScrollState: ScrollState,
    horizontalScrollState: ScrollState,
): Modifier {
    var newModifier = this
    when (direction) {
        ScrollViewDirection.Both -> {
            if (scrollIndicator) {
                newModifier = newModifier.drawVerticalScrollbar(verticalScrollState)
                newModifier = newModifier.drawHorizontalScrollbar(horizontalScrollState)
            }
            newModifier = newModifier.verticalScroll(verticalScrollState)
            newModifier = newModifier.horizontalScroll(horizontalScrollState)
        }
        ScrollViewDirection.Horizontal -> {
            if (scrollIndicator) {
                newModifier = newModifier.drawHorizontalScrollbar(horizontalScrollState)
            }
            newModifier = newModifier.horizontalScroll(horizontalScrollState)
        }
        ScrollViewDirection.Vertical -> {
            if (scrollIndicator) {
                newModifier = newModifier.drawVerticalScrollbar(verticalScrollState)
            }
            newModifier = newModifier.verticalScroll(verticalScrollState)
        }
    }

    return newModifier
}
