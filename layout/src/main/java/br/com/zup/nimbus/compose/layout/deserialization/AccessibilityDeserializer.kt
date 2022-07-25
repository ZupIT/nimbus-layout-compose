package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.zup.com.nimbus.compose.ComponentData
import com.zup.nimbus.processor.TypeDeserializer

// FIXME: this should not be necessary once we complete the auto-deserialization task.
object AccessibilityDeserializer: TypeDeserializer<Accessibility?> {
    override fun deserialize(data: Any): Accessibility? {
        data as ComponentData
        val accessibility = data.node.properties?.get("accessibility")
        if (accessibility !is Map<*, *>) return null
        val label = accessibility["label"] as? String
        val isHeader = accessibility["isHeader"] as? Boolean
        return Accessibility(label, isHeader)
    }
}
