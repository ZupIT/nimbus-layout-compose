package br.com.zup.nimbus.compose.layout.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.COLOR_WHITE
import br.com.zup.nimbus.compose.layout.model.Box

internal fun Modifier.applyBackgroundForShadow(
    container: Box,
    modifier: Modifier = Modifier,
) = this.then(with(container) {
    var newModifier = modifier
    container.shadow?.let { shadowList ->
        if (shadowList.isNotEmpty() && container.backgroundColor == null) {
            newModifier = newModifier.background(COLOR_WHITE)
        }
    }

    return@with newModifier
}
)

internal fun Modifier.shadow(
    container: Box,
    modifier: Modifier = Modifier,
) = this.then(with(container) {
    var newModifier = modifier
    container.shadow?.let { shadowList ->
        shadowList.forEach { shadow ->
            var offsetX: Dp = 0.dp // default: 0
            var offsetY: Dp = 0.dp // default: 0
            var blur: Dp = 0.dp // blur default: 0
            var borderRadius: Dp = 0.dp // blur default: 0
            var color: Color = Color.Black // default: black
            var spread = 0f
            var inset = false

            container.cornerRadius?.let {
                borderRadius = it.dp
            }

            shadow.x?.let {
                offsetX = it.dp
            }

            shadow.y?.let {
                offsetY = it.dp
            }

            shadow.blur?.let {
                blur = it.dp
            }

            shadow.color?.let {
                color = it.color
            }

            shadow.spread?.let {
                spread = it.toFloat()
            }

            shadow.inset?.let {
                inset = it
            }

            newModifier = newModifier.coloredShadow(
                borderRadius = borderRadius,
                offsetX = offsetX,
                offsetY = offsetY,
                blurRadius = blur,
                color = color,
                spread = spread,
//                    inset = inset
            )
        }
    }
    newModifier = newModifier.applyBackgroundForShadow(container)
    return@with newModifier
}
)

//TODO implement negative spread logic, for now its only working with positive values
//TODO implement inset logic
internal fun Modifier.coloredShadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Float = 0f,
//    inset: Boolean = false,
    modifier: Modifier = Modifier,
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.dp.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel =  (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                /*
                    The feature maskFilter used below to apply the blur effect only works
                    with hardware acceleration disabled.
                    This is accomplished by the composable below
                    @see br.com.zup.nimbus.compose.layout.NimbusSoftwareLayer
                 */
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)
