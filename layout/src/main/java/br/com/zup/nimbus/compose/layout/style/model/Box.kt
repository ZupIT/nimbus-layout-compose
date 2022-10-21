package br.com.zup.nimbus.compose.layout.style.model

internal open class Box(
    override val borderWidth: Double?,
    override val borderDashLength: Double?,
    override val borderDashSpacing: Double?,
    override val cornerRadius: Double?,
    override val borderColor: String?,
    override val margin: Double?,
    override val marginStart: Double?,
    override val marginEnd: Double?,
    override val marginTop: Double?,
    override val marginBottom: Double?,
    override val marginHorizontal: Double?,
    override val marginVertical: Double?,
    override val padding: Double?,
    override val paddingStart: Double?,
    override val paddingEnd: Double?,
    override val paddingTop: Double?,
    override val paddingBottom: Double?,
    override val paddingHorizontal: Double?,
    override val paddingVertical: Double?,
    width: AdaptiveSize? = null,
    height: AdaptiveSize? = null,
    directionScope: DirectionScope,
    minWidth: Double?,
    minHeight: Double?,
    maxWidth: Double?,
    maxHeight: Double?,
    clipped: Boolean?,
    val backgroundColor: String?,
    val shadow: List<Shadow>? = null,
) : Size(
    width = width,
    height = height,
    directionScope = directionScope,
    minWidth = minWidth,
    minHeight = minHeight,
    maxWidth = maxWidth,
    maxHeight = maxHeight,
    clipped = clipped,
), Margin, Padding, Border {
    fun shouldDisableHardwareAcceleration(): Boolean {
        return shadow?.let { shadowList ->
            shadowList.any { it.blur != null && it.blur > 0.0 }
        } ?: false
    }
}
