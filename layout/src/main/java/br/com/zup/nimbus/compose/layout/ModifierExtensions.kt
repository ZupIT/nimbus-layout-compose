package br.com.zup.nimbus.compose.layout

import android.graphics.BlurMaskFilter
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.com.zup.nimbus.compose.layout.model.COLOR_WHITE
import br.com.zup.nimbus.compose.layout.model.ComponentNames
import br.com.zup.nimbus.compose.layout.model.ComponentStructure
import br.com.zup.nimbus.compose.layout.model.Container
import br.com.zup.nimbus.compose.layout.model.CrossAxisAlignment


internal fun Modifier.accessibility(accessibility: Accessibility?, modifier: Modifier = Modifier) = this.then(
    accessibility?.let { a ->
        modifier.semantics(mergeDescendants = true) {
            a.label?.let {
                contentDescription = it
            }
            if (a.isHeader == true) {
                heading()
            }
        }
    } ?: modifier
)

internal fun Modifier.background(color: String?, modifier: Modifier = Modifier) = this.then(
    color?.let { modifier.background(it.color) } ?: modifier
)

internal fun Modifier.clipped(clipped: Boolean?, modifier: Modifier = Modifier) = this.then(
    clipped?.let { if(clipped) modifier.clipToBounds() else modifier } ?: modifier
)

internal fun Modifier.container(
    container: Container,
    parentComponent: ComponentStructure? = null,
    @LayoutScopeMarker
    scope: Any,
    modifier: Modifier = Modifier,
) = this.then(
    modifier
        .applyScopeModifier(scope, container)
        .margin(container)
        .clipped(container.clipped)
        .size(container)
        .fillMaxSize(container, parentComponent)
        .shadow(container)
        .border(container)
        .background(container.backgroundColor)
        .padding(container)
)

internal fun Modifier.applyScopeModifier(
    scope: Any,
    container: Container,
    modifier: Modifier = Modifier,
) = this.then(with(container) {
    var newModifier = modifier
    when (scope) {
        is RowScope -> {
            with(scope)
            {
                container.flex?.let {
                    newModifier = newModifier.weight(it.toFloat())
                }

                container.crossAxisAlignment?.let { crossAxis ->
                    if (crossAxis == CrossAxisAlignment.STRETCH) {
                        newModifier = newModifier.height(IntrinsicSize.Min)
                    }
                }
            }
        }
        is ColumnScope -> {
            with(scope) {
                container.flex?.let {
                    newModifier = newModifier.weight(it.toFloat())
                }

                container.crossAxisAlignment?.let { crossAxis ->
                    if (crossAxis == CrossAxisAlignment.STRETCH) {
                        newModifier = newModifier.width(IntrinsicSize.Min)
                    }
                }
            }
        }
        else -> {}
    }
    return@with newModifier
})

internal fun Modifier.fillMaxSize(
    container: Container,
    parentComponent: ComponentStructure?,
    modifier: Modifier = Modifier,
) = this.then(
    with(container) {
        var newModifier = modifier

        if (parentComponent != null) {
            if (parentComponent.component == ComponentNames.NIMBUS_ROW) {
                if (container.height == null) {
                    parentComponent.properties?.crossAxisAlignment?.let { crossAxis ->
                        if (crossAxis == CrossAxisAlignment.STRETCH) {
                            newModifier = newModifier.fillMaxHeight()
                        }
                    }
                }
            } else if (parentComponent.component == ComponentNames.NIMBUS_COLUMN) {
                if (container.width == null) {
                    parentComponent.properties?.crossAxisAlignment?.let { crossAxis ->
                        if (crossAxis == CrossAxisAlignment.STRETCH) {
                            newModifier = newModifier.fillMaxWidth()
                        }
                    }
                }
            }
        }

        return@with newModifier
    }
)

internal fun Modifier.size(
    container: Container,
    modifier: Modifier = Modifier,
) = this.then(
    with(container) {
        var newModifier = modifier

        container.width?.let {
            newModifier = newModifier.width(it.dp)
        }

        container.height?.let {
            newModifier = newModifier.height(it.dp)
        }

        container.minHeight?.let {
            newModifier = newModifier.heightIn(min = it.dp)
        }

        container.maxHeight?.let {
            newModifier = newModifier.heightIn(max = it.dp)
        }

        container.minWidth?.let {
            newModifier = newModifier.heightIn(min = it.dp)
        }

        container.maxWidth?.let {
            newModifier = newModifier.heightIn(max = it.dp)
        }

        return@with newModifier
    }
)

internal fun Modifier.margin(
    container: Container,
    modifier: Modifier = Modifier,
) = this.then(
    with(container) {
        var newModifier = modifier

        container.margin?.let {
            newModifier = newModifier.padding(it.dp)
        }

        container.marginStart?.let {
            newModifier = newModifier.padding(start = it.dp)
        }

        container.marginEnd?.let {
            newModifier = newModifier.padding(end = it.dp)
        }

        container.marginTop?.let {
            newModifier = newModifier.padding(top = it.dp)
        }

        container.marginBottom?.let {
            newModifier = newModifier.padding(bottom = it.dp)
        }

        container.marginHorizontal?.let {
            newModifier = newModifier.padding(start = it.dp)
            newModifier = newModifier.padding(end = it.dp)
        }

        container.marginVertical?.let {
            newModifier = newModifier.padding(top = it.dp)
            newModifier = newModifier.padding(bottom = it.dp)
        }
        return@with newModifier
    }
)

internal fun Modifier.padding(
    container: Container,
    modifier: Modifier = Modifier,
) = this.then(
    with(container) {
        var newModifier = modifier

        container.padding?.let {
            newModifier = newModifier.padding(it.dp)
        }

        container.paddingStart?.let {
            newModifier = newModifier.padding(start = it.dp)
        }

        container.paddingEnd?.let {
            newModifier = newModifier.padding(end = it.dp)
        }

        container.paddingTop?.let {
            newModifier = newModifier.padding(top = it.dp)
        }

        container.paddingBottom?.let {
            newModifier = newModifier.padding(bottom = it.dp)
        }

        container.paddingHorizontal?.let {
            newModifier = newModifier.padding(start = it.dp)
            newModifier = newModifier.padding(end = it.dp)
        }

        container.paddingVertical?.let {
            newModifier = newModifier.padding(top = it.dp)
            newModifier = newModifier.padding(bottom = it.dp)
        }

        return@with newModifier
    }
)

internal fun Modifier.border(
    container: Container,
    modifier: Modifier = Modifier,
) = this.then(
    with(container) {
        var newModifier = modifier
            var borderWidth = 0.0
            var borderDashLength =  1.0
            var borderDashSpacing = 0.0
            var cornerRadius = 0.0
            var color: Color = Color.Black // default: black

            container.borderWidth?.let {
                borderWidth = it
            }

            container.borderDashLength?.let {
                borderDashLength = it
            }

            container.borderDashSpacing?.let {
                borderDashSpacing = it
            }

            container.cornerRadius?.let {
                cornerRadius = it
            }

            container.borderColor?.let {
                color = it.color
            }

            newModifier = newModifier.border(
                borderWidth = borderWidth,
                borderDashLength = borderDashLength,
                borderDashSpacing = borderDashSpacing,
                cornerRadius = cornerRadius,
                color = color
            )
        return@with newModifier
    }
)

internal fun Modifier.applyBackgroundForShadow(
    container: Container,
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
    container: Container,
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

internal fun Modifier.border(
    borderWidth: Double = 0.0,
    borderDashLength: Double = 1.0,
    borderDashSpacing: Double = 0.0,
    cornerRadius: Double = 0.0,
    color: Color,
    modifier: Modifier = Modifier,
) = this.then(
    modifier
        .drawBehind {
            applyBorder(borderWidth, borderDashLength, borderDashSpacing, cornerRadius, color)
            clipToRadius(cornerRadius)
        }
)

private fun DrawScope.applyBorder(
    borderWidth: Double,
    borderDashLength: Double,
    borderDashSpacing: Double,
    cornerRadius: Double,
    color: Color,
) {
    if (borderWidth != 0.0) {
        val dottedStroke = Stroke(
            width = borderWidth.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(borderDashLength.dp.toPx(),
                    (borderDashSpacing).dp.toPx()), 0f)
        )

        this.drawIntoCanvas {
            drawRoundRect(cornerRadius =
            CornerRadius(cornerRadius.dp.toPx()), color = color, style = dottedStroke)
        }
    }
}

private fun DrawScope.clipToRadius(cornerRadius: Double) {
    this.drawIntoCanvas {
        val path = Path()
        path.addRoundRect(RoundRect(left = 0f,
            top = 0f,
            right = (this.size.width),
            bottom = (this.size.height),
            cornerRadius = CornerRadius(cornerRadius.dp.toPx(), cornerRadius.dp.toPx())))
        it.clipPath(path)
    }
}

val String.color
    get() = Color(android.graphics.Color.parseColor(this))
