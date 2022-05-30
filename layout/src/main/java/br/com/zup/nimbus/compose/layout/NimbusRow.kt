package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.Shadow
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

private val kClass = Class.forName("androidx.compose.foundation.layout.RowScopeInstance").kotlin
private val scope = kClass.objectInstance ?: kClass.java.newInstance() as RowScope

@Composable
internal fun NimbusRow(
    model: RowModel,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier.container(container = model, scope = scope)
    ) {
        content()
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class RowModel(
    override val backgroundColor: String? = null,
    override val shadow: List<Shadow>? = null,
    override var children: Component? = null,
    override val flex: Int? = null,
    override val crossAxisAlignment: String? = "start",
    override val mainAxisAlignment: String? = "start",
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val borderWidth: Double? = 0.0,
    override val borderDashLength: Double? = 1.0,
    override val borderDashSpacing: Double? = 0.0,
    override val cornerRadius: Double? = 0.0,
    override val margin: Double? = null,
    override val marginStart: Double? = null,
    override val marginEnd: Double? = null,
    override val marginTop: Double? = null,
    override val marginBottom: Double? = null,
    override val marginHorizontal: Double? = null,
    override val marginVertical: Double? = null,
    override val padding: Double? = null,
    override val paddingStart: Double? = null,
    override val paddingEnd: Double? = null,
    override val paddingTop: Double? = null,
    override val paddingBottom: Double? = null,
    override val paddingHorizontal: Double? = null,
    override val paddingVertical: Double? = null
) : Container
