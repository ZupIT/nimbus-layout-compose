package br.com.zup.nimbus.compose.layout.style.model

import com.zup.nimbus.processor.annotation.Root

internal open class Box(
    @Root val margin: Margin,
    @Root val padding: Padding,
    @Root val border: Border,
    @Root val size: Size,
    val backgroundColor: String?,
    val shadow: List<Shadow>?,
) {
    fun shouldDisableHardwareAcceleration(): Boolean {
        return shadow?.let { shadowList ->
            shadowList.any { it.blur != null && it.blur > 0.0 }
        } ?: false
    }
}
