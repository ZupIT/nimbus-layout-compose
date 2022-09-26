package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.DirectionScope
import br.zup.com.nimbus.compose.ComponentData
import br.zup.com.nimbus.compose.TypeDeserializer
import com.zup.nimbus.core.deserialization.ComponentDeserializer

object DirectionScopeDeserializer: TypeDeserializer<DirectionScope> {
    override fun deserialize(
        properties: ComponentDeserializer,
        data: ComponentData,
        name: String
    ): DirectionScope {
        // TODO: expose this logic to the user. It must be possible to create non-leaf custom
        //  components that doesn't ruin the Size properties.
        return when(data.parent?.component) {
            "layout:row", "layout:flowRow" -> DirectionScope.Row
            "layout:stack" -> DirectionScope.Stack
            else -> DirectionScope.Column
        }
    }
}
