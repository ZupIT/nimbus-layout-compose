package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
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

private object NimbusRowScope {
    private val kClass =
        Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
    val instance by lazy { kClass.objectInstance ?: kClass.java.newInstance() as RowScope }
}

private fun Modifier.rowParentStretch(hasChildStretch: Boolean): Modifier {
    return if (hasChildStretch) this.height(IntrinsicSize.Min) else this
}

@Composable
@ServerDrivenComponent
internal fun Row(
    @Root style: Container,
    @ParentName parentComponentName: String? = null,
    @Computed<StretchDeserializer>(StretchDeserializer::class) hasChildStretch: Boolean,
    content: @Composable () -> Unit,
) {
    val mainAxisAlignment = style.mainAxisAlignment ?: MainAxisAlignment.Start
    val crossAxisAlignment = style.crossAxisAlignment ?: CrossAxisAlignment.Start
    val horizontalArrangement = mainAxisAlignment.toHorizontalArrangement()
    val verticalAlignment = crossAxisAlignment.toVerticalAlignment()
    NimbusSoftwareLayer(condition = style.shouldDisableHardwareAcceleration()) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            modifier = Modifier
                .rowParentStretch(hasChildStretch)
                .containerStyle(style, parentComponentName, NimbusRowScope.instance)
        ) {
            content()
        }
    }
}


