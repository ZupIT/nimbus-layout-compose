package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.extensions.color
import br.com.zup.nimbus.compose.layout.style.model.Border

internal fun Modifier.borderStyle(style: Border): Modifier {
    var borderWidth = 0.0
    var borderDashLength = 1.0
    var borderDashSpacing = 0.0
    var cornerRadius = 0.0
    var color: Color = Color.Black // default: black

    style.borderWidth?.let {
        borderWidth = it
    }

    style.borderDashLength?.let {
        borderDashLength = it
    }

    style.borderDashSpacing?.let {
        borderDashSpacing = it
    }

    style.cornerRadius?.let {
        cornerRadius = it
    }

    style.borderColor?.let {
        color = it.color
    }

    return this.border(
        borderWidth = borderWidth,
        borderDashLength = borderDashLength,
        borderDashSpacing = borderDashSpacing,
        cornerRadius = cornerRadius,
        color = color
    )
}

internal fun Modifier.border(
    borderWidth: Double = 0.0,
    borderDashLength: Double = 1.0,
    borderDashSpacing: Double = 0.0,
    cornerRadius: Double = 0.0,
    color: Color,
    modifier: Modifier = Modifier
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
