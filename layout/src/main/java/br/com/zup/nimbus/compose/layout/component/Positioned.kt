package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Positioned
import br.com.zup.nimbus.compose.layout.style.modifier.positionedStyle
import com.zup.nimbus.processor.annotation.AutoDeserialize
import com.zup.nimbus.processor.annotation.Root

private object NimbusBoxScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.BoxScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as BoxScope }
}

@Composable
@AutoDeserialize
internal fun Positioned(
    @Root style: Positioned,
    content: @Composable () -> Unit,
) {
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Column(
            modifier = Modifier.positionedStyle(style = style, scope = NimbusBoxScope.instance)
        ) {
            content()
        }
    }
}
