package br.com.zup.nimbus.compose.layout.deserialization

fun deserializeDouble(anyValue: Any?): Double? {
    return when (anyValue) {
        is Double -> anyValue
        is Int -> anyValue.toDouble()
        is Float -> anyValue.toDouble()
        else -> null
    }
}

fun deserializeInt(anyValue: Any?): Int? {
    return when (anyValue) {
        is Double -> anyValue.toInt()
        is Int -> anyValue
        is Float -> anyValue.toInt()
        else -> null
    }
}
