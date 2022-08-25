package br.com.zup.nimbus.compose.layout.deserialization

import br.zup.com.nimbus.compose.ComponentData
import com.zup.nimbus.processor.TypeDeserializer

object StretchDeserializer: TypeDeserializer<Boolean> {
    override fun deserialize(data: Any): Boolean {
        // data is always a ComponentData. This is an issue that we still need to solve in the
        // processor library.
        data as ComponentData
        return data.node.children?.any { it.properties?.get("stretch") == true } ?: false
    }

}
