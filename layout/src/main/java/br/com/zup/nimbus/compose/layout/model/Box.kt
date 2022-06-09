package br.com.zup.nimbus.compose.layout.model

internal interface Box : Margin, Padding, Size, Border {
    val backgroundColor: String?
    val shadow: List<Shadow>?
    val children: Component?
}


internal fun Box.shouldDisableHardwareAcceleration(): Boolean {
    return shadow?.let { shadowList ->
        shadowList.any { it.blur != null && it.blur > 0.0 }
    } ?: false
}
