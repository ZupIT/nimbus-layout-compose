package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.ui.Alignment
import com.zup.nimbus.processor.annotation.Root

internal class Positioned(
    @Root val box: Box,
    val alignment: PositionedAlignment?,
    val x: Double?,
    val y: Double?,
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
