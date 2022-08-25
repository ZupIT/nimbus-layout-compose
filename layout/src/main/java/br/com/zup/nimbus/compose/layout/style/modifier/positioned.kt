package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.Positioned
import br.com.zup.nimbus.compose.layout.style.model.PositionedAlignment

internal fun Modifier.positionedStyle(
    style: Positioned,
    @LayoutScopeMarker
    scope: Any,
) = this.then(
    Modifier.applyScopeModifier(scope = scope, positioned = style)
    .absoluteOffset(x = style.x?.dp ?: 0.dp)
    .absoluteOffset(y = style.y?.dp ?: 0.dp)
    .boxStyle(style)
)

internal fun Modifier.applyScopeModifier(
    scope: Any? = null,
    positioned: Positioned,
    modifier: Modifier = Modifier,
) = this.then(with(positioned) {
    if (scope == null) return@with modifier
    var newModifier = modifier
    when (scope) {
        is BoxScope -> {
            with(scope)
            {
                val nimbusAlignment = positioned.alignment ?: PositionedAlignment.TopStart
                val alignment = nimbusAlignment.toAlignment()
                newModifier = newModifier.align(alignment)
            }
        }
    }
    return@with newModifier
})
