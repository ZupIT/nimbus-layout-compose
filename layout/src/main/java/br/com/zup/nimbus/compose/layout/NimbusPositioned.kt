package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.positioned
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.NimbusPositionedApi
import br.com.zup.nimbus.compose.layout.model.shouldDisableHardwareAcceleration

private object NimbusBoxScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.BoxScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as BoxScope }
}

@Composable
internal fun NimbusPositioned(
    model: NimbusPositionedApi,
    modifier: Modifier = Modifier,
    content: Component,
) {
    val positioned = requireNotNull(model.properties)

    NimbusSoftwareLayer(condition = positioned.shouldDisableHardwareAcceleration()) {
        Column(
            modifier = modifier.positioned(positioned = positioned, scope = NimbusBoxScope.instance)
        ) {
            content()
        }
    }

}
