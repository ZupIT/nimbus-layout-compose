package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.COLOR_BLACK
import br.com.zup.nimbus.compose.layout.style.model.Shadow
import br.zup.com.nimbus.compose.ComponentData
import br.zup.com.nimbus.compose.TypeDeserializer
import com.zup.nimbus.core.deserialization.ComponentDeserializer
import com.zup.nimbus.core.deserialization.MapDeserializer

object ShadowDeserializer: TypeDeserializer<List<Shadow>?> {
    private fun deserializeShadowMap(data: Any?): Shadow {
        val shadowMap = try {
            @Suppress("UNCHECKED_CAST")
            data as Map<String, Any?>
        } catch(_: ClassCastException) {
            return Shadow()
        }
        val deserializer = MapDeserializer()
        deserializer.start(shadowMap)
        return Shadow(
            x = deserializer.asDoubleOrNull("x") ?: 0.0,
            y = deserializer.asDoubleOrNull("y") ?: 0.0,
            blur = deserializer.asDoubleOrNull("blur") ?: 0.0,
            color = deserializer.asStringOrNull("color") ?: COLOR_BLACK,
        )
    }

    override fun deserialize(
        properties: ComponentDeserializer,
        data: ComponentData,
        name: String,
    ): List<Shadow>? {
        // it would be nice to have something like mapList in the deserializer
        val shadowMapList = properties.asListOrNull("shadow")
        return shadowMapList?.map { deserializeShadowMap(it) }
    }
}
