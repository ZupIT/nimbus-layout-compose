package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.deserialization.StretchDeserializer
import br.com.zup.nimbus.compose.layout.style.model.Container
import br.com.zup.nimbus.compose.layout.style.model.CrossAxisAlignment
import br.com.zup.nimbus.compose.layout.style.model.MainAxisAlignment
import br.com.zup.nimbus.compose.layout.style.modifier.containerStyle
import com.zup.nimbus.processor.Computed
import com.zup.nimbus.processor.ParentName
import com.zup.nimbus.processor.Root
import com.zup.nimbus.processor.ServerDrivenComponent

private object NimbusColumnScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.ColumnScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as ColumnScope }
}

private fun Modifier.columnParentStretch(hasChildStretch: Boolean): Modifier {
    return if (hasChildStretch) this.width(IntrinsicSize.Min) else this
}

@Composable
@ServerDrivenComponent
internal fun Column(
    @Root style: Container,
    @ParentName parentComponentName: String? = null,
    @Computed<StretchDeserializer>(StretchDeserializer::class) hasChildStretch: Boolean,
    content: @Composable () -> Unit,
) {
    val mainAxisAlignment = style.mainAxisAlignment ?: MainAxisAlignment.Start
    val crossAxisAlignment = style.crossAxisAlignment ?: CrossAxisAlignment.Start
    val verticalArrangement = mainAxisAlignment.toVerticalArrangement()
    val horizontalAlignment = crossAxisAlignment.toHorizontalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Column(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            modifier = Modifier
                .columnParentStretch(hasChildStretch)
                .containerStyle(style, parentComponentName, NimbusColumnScope.instance)
        ) {
            content()
        }
    }
}
