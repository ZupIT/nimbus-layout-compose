package br.com.zup.nimbus.compose.layout.style.model

import br.com.zup.nimbus.annotation.Root

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

    fun copy(
        omitMargin: Boolean = false,
        omitPadding: Boolean = false,
        omitBorder: Boolean = false,
        omitSize: Boolean = false,
        omitBackgroundColor: Boolean = false,
        omitShadow: Boolean = false,
    ) = Box(
        margin = if (omitMargin) Margin.empty else margin,
        padding = if (omitPadding) Padding.empty else padding,
        border = if (omitBorder) Border.empty else border,
        size = if (omitSize) Size.empty else size,
        backgroundColor = if (omitBackgroundColor) null else backgroundColor,
        shadow = if (omitShadow) null else shadow,
    )
}
