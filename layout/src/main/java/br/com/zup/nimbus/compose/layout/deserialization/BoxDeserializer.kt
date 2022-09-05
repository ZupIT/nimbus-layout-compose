package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.Box
import br.zup.com.nimbus.compose.ComponentData
import com.zup.nimbus.processor.TypeDeserializer

internal object BoxDeserializer: TypeDeserializer<Box> {
    override fun deserialize(data: Any): Box {
        data as ComponentData

        @Suppress("UNCHECKED_CAST")
        return Box(
            deserializeDouble(data.node.properties?.get("borderWidth")),
            deserializeDouble(data.node.properties?.get("borderDashLength")),
            deserializeDouble(data.node.properties?.get("borderDashSpacing")),
            deserializeDouble(data.node.properties?.get("cornerRadius")),
            data.node.properties?.get("borderColor") as? String?,
            deserializeDouble(data.node.properties?.get("margin")),
            deserializeDouble(data.node.properties?.get("marginStart")),
            deserializeDouble(data.node.properties?.get("marginEnd")),
            deserializeDouble(data.node.properties?.get("marginTop")),
            deserializeDouble(data.node.properties?.get("marginBottom")),
            deserializeDouble(data.node.properties?.get("marginHorizontal")),
            deserializeDouble(data.node.properties?.get("marginVertical")),
            deserializeDouble(data.node.properties?.get("padding")),
            deserializeDouble(data.node.properties?.get("paddingStart")),
            deserializeDouble(data.node.properties?.get("paddingEnd")),
            deserializeDouble(data.node.properties?.get("paddingTop")),
            deserializeDouble(data.node.properties?.get("paddingBottom")),
            deserializeDouble(data.node.properties?.get("paddingHorizontal")),
            deserializeDouble(data.node.properties?.get("paddingVertical")),
            handleWidthDeserialization(data.node.properties?.get("width")),
            handleHeightDeserialization(data.node.properties?.get("height")),
            deserializeDouble(data.node.properties?.get("minWidth")),
            deserializeDouble(data.node.properties?.get("minHeight")),
            deserializeDouble(data.node.properties?.get("maxWidth")),
            deserializeDouble(data.node.properties?.get("maxHeight")),
            data.node.properties?.get("clipped") as? Boolean?,
            data.node.properties?.get("backgroundColor") as? String?,
            deserializeShadow(data.node.properties?.get("shadow")),
        )
    }
}
