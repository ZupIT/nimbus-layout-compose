package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.ui.Alignment
import com.zup.nimbus.processor.Ignore

internal class Positioned(
    borderWidth: Double?,
    borderDashLength: Double?,
    borderDashSpacing: Double?,
    cornerRadius: Double?,
    borderColor: String?,
    margin: Double?,
    marginStart: Double?,
    marginEnd: Double?,
    marginTop: Double?,
    marginBottom: Double?,
    marginHorizontal: Double?,
    marginVertical: Double?,
    padding: Double?,
    paddingStart: Double?,
    paddingEnd: Double?,
    paddingTop: Double?,
    paddingBottom: Double?,
    paddingHorizontal: Double?,
    paddingVertical: Double?,
    width: Double?,
    height: Double?,
    minWidth: Double?,
    minHeight: Double?,
    maxWidth: Double?,
    maxHeight: Double?,
    clipped: Boolean?,
    backgroundColor: String?,
    @Ignore shadow: List<Shadow>? = null,
    val alignment: PositionedAlignment?,
    val x: Double?,
    val y: Double?,
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
    minWidth = minWidth,
    minHeight = minHeight,
    maxWidth = maxWidth,
    maxHeight = maxHeight,
    clipped = clipped,
    backgroundColor = backgroundColor,
    shadow = shadow,
)

internal enum class PositionedAlignment {
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
