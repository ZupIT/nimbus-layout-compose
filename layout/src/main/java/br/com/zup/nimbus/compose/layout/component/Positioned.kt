package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.positioned
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.PositionedModel
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

private object NimbusBoxScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.BoxScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as BoxScope }
}

@Composable
internal fun Positioned(
    model: PositionedModel,
    modifier: Modifier = Modifier,
    content: Component,
) {
    NimbusSoftwareLayer(condition = model.shouldDisableHardwareAcceleration()) {
        Column(
            modifier = modifier.positioned(positioned = model, scope = NimbusBoxScope.instance)
        ) {
            content()
        }
    }

}
