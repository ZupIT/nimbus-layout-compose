package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.component.drawHorizontalScrollbar
import br.com.zup.nimbus.compose.layout.component.drawVerticalScrollbar
import br.com.zup.nimbus.compose.layout.model.ScrollViewModel
import br.com.zup.nimbus.compose.layout.model.ScrollViewDirection

internal fun Modifier.scroll(
    scrollView: ScrollViewModel,
    verticalScrollState: ScrollState,
    horizontalScrollState: ScrollState,
    modifier: Modifier = Modifier,
) = this.then(with(scrollView) {
    var newModifier = modifier
    val direction = requireNotNull(scrollView.direction)
    val showIndicator: Boolean = scrollView.scrollIndicator == true
    when (direction) {
        ScrollViewDirection.BOTH -> {
            if (showIndicator) {
                newModifier = newModifier.drawVerticalScrollbar(verticalScrollState)
                newModifier = newModifier.drawHorizontalScrollbar(horizontalScrollState)
            }
            newModifier = newModifier.verticalScroll(verticalScrollState)
            newModifier = newModifier.horizontalScroll(horizontalScrollState)
        }
        ScrollViewDirection.HORIZONTAL -> {
            if (showIndicator) {
                newModifier = newModifier.drawHorizontalScrollbar(horizontalScrollState)
            }
            newModifier = newModifier.horizontalScroll(horizontalScrollState)
        }
        ScrollViewDirection.VERTICAL -> {
            if (showIndicator) {
                newModifier = newModifier.drawVerticalScrollbar(verticalScrollState)
            }
            newModifier = newModifier.verticalScroll(verticalScrollState)
        }
    }

    return@with newModifier
})
