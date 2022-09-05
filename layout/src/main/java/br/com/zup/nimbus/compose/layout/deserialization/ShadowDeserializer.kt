package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.Shadow

fun deserializeShadow(rawShadow: Any?): List<Shadow>? {
    if (rawShadow == null || rawShadow !is List<*>) return null
    val shadow = mutableListOf<Shadow>()
    rawShadow.forEach {
        if (it is Map<*, *>) {
            shadow.add(
                Shadow(
                    deserializeDouble(it["x"]),
                    deserializeDouble(it["y"]),
                    deserializeDouble(it["blur"]),
                    it["color"] as? String?,
                    deserializeInt(it["spread"]),
                    it["inset"] as? Boolean?
                )
            )
        }
    }
    return shadow
}
