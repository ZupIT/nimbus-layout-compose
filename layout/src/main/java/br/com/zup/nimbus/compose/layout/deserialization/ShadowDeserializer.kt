package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.Shadow

fun deserializeShadow(rawShadow: Any?): List<Shadow>? {
    if (rawShadow == null || rawShadow !is List<*>) return null
    val shadow = mutableListOf<Shadow>()
    for (raw in rawShadow) {
        if (raw is Map<*, *>) {
            shadow.add(
                Shadow(
                    deserializeDouble(raw["x"]),
                    deserializeDouble(raw["y"]),
                    deserializeDouble(raw["blur"]),
                    raw["color"] as? String?,
                    deserializeInt(raw["spread"]),
                    raw["inset"] as? Boolean?
                )
            )
        }
    }
    return shadow
}
