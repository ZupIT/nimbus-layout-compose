package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.ui.Alignment
import br.com.zup.nimbus.compose.layout.deserialization.AdaptiveSizeDeserializer
import br.com.zup.nimbus.compose.layout.deserialization.DirectionScopeDeserializer
import br.com.zup.nimbus.compose.layout.deserialization.ShadowDeserializer
import com.zup.nimbus.processor.Computed
import com.zup.nimbus.processor.Root

internal class Positioned(
    borderWidth: Double? = null,
    borderDashLength: Double? = null,
    borderDashSpacing: Double? = null,
    cornerRadius: Double? = null,
    borderColor: String? = null,
    margin: Double? = null,
    marginStart: Double? = null,
    marginEnd: Double? = null,
    marginTop: Double? = null,
    marginBottom: Double? = null,
    marginHorizontal: Double? = null,
    marginVertical: Double? = null,
    padding: Double? = null,
    paddingStart: Double? = null,
    paddingEnd: Double? = null,
    paddingTop: Double? = null,
    paddingBottom: Double? = null,
    paddingHorizontal: Double? = null,
    paddingVertical: Double? = null,
    @Root @Computed(AdaptiveSizeDeserializer::class) width: AdaptiveSize? = null,
    @Root @Computed(AdaptiveSizeDeserializer::class) height: AdaptiveSize? = null,
    @Root @Computed(DirectionScopeDeserializer::class) directionScope: DirectionScope,
    minWidth: Double? = null,
    minHeight: Double? = null,
    maxWidth: Double? = null,
    maxHeight: Double? = null,
    clipped: Boolean? = null,
    backgroundColor: String? = null,
    @Root @Computed(ShadowDeserializer::class) shadow: List<Shadow>? = null,
    val alignment: PositionedAlignment? = null,
    val x: Double? = null,
    val y: Double? = null,
): Box(
    borderWidth = borderWidth,
    borderDashLength = borderDashLength,
    borderDashSpacing = borderDashSpacing,
    cornerRadius = cornerRadius,
    borderColor = borderColor,
    margin = margin,
    marginStart = marginStart,
    marginEnd = marginEnd,
    marginTop = marginTop,
    marginBottom = marginBottom,
    marginHorizontal = marginHorizontal,
    marginVertical = marginVertical,
    padding = padding,
    paddingStart = paddingStart,
    paddingEnd = paddingEnd,
    paddingTop = paddingTop,
    paddingBottom = paddingBottom,
    paddingHorizontal = paddingHorizontal,
    paddingVertical = paddingVertical,
    width = width,
    height = height,
    directionScope = directionScope,
    minWidth = minWidth,
    minHeight = minHeight,
    maxWidth = maxWidth,
    maxHeight = maxHeight,
    clipped = clipped,
    backgroundColor = backgroundColor,
    shadow = shadow,
)

enum class PositionedAlignment {
    TopStart,
    TopEnd,
    BottomStart,
    BottomEnd,
    TopCenter,
    BottomCenter,
    CenterStart,
    CenterEnd,
    Center;

    fun toAlignment(): Alignment = when (this) {
        TopStart -> Alignment.TopStart
        TopEnd -> Alignment.TopEnd
        BottomStart -> Alignment.BottomStart
        BottomEnd -> Alignment.BottomEnd
        TopCenter -> Alignment.TopCenter
        BottomCenter -> Alignment.BottomCenter
        CenterStart -> Alignment.CenterStart
        CenterEnd -> Alignment.CenterEnd
        Center -> Alignment.Center
    }
}
