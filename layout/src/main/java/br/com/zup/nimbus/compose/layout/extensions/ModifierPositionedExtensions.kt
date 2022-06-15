package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.model.Positioned

internal fun Modifier.positioned(
    positioned: Positioned,
    @LayoutScopeMarker
    scope: Any,
    modifier: Modifier = Modifier,
) = this.then(
    modifier
        .applyScopeModifier(scope = scope, positioned = positioned)
        .offset(x = positioned.x?.dp ?: 0.dp)
        .offset(y = positioned.y?.dp ?: 0.dp)
        .margin(positioned)
        .size(positioned)
        .shadow(positioned)
        .border(positioned)
        .background(positioned.backgroundColor)
        .padding(positioned)
)

internal fun Modifier.applyScopeModifier(
    scope: Any,
    positioned: Positioned,
    modifier: Modifier = Modifier,
) = this.then(with(positioned) {
    var newModifier = modifier
    when (scope) {
        is BoxScope -> {
            with(scope)
            {
                val nimbusAlignment = requireNotNull(positioned.alignmentSelf)
                val alignment = nimbusAlignment.toAlignment()
                newModifier = newModifier.align(alignment)
            }
        }
    }
    return@with newModifier
})
