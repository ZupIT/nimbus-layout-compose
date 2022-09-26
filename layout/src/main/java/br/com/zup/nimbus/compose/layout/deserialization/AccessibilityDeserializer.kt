package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.zup.com.nimbus.compose.ComponentData
import br.zup.com.nimbus.compose.TypeDeserializer
import com.zup.nimbus.core.deserialization.ComponentDeserializer

// FIXME: this should not be necessary once we complete the auto-deserialization task.
object AccessibilityDeserializer: TypeDeserializer<Accessibility?> {
    override fun deserialize(
        properties: ComponentDeserializer,
        data: ComponentData,
        name: String,
    ): Accessibility? {
        val label = properties.asStringOrNull("label")
        val isHeader = properties.asBooleanOrNull("isHeader")
        return if (label != null || isHeader != null) Accessibility(label, isHeader) else null
    }
}
