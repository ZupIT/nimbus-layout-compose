package br.com.zup.nimbus.compose.layout.component.image

import androidx.compose.ui.layout.ContentScale

internal enum class ImageScale {
    FillHeight,
    FillWidth,
    FillBounds,
    Center;

    fun toContentScale(): ContentScale = when (this) {
        FillHeight -> ContentScale.Crop
        FillWidth -> ContentScale.Fit
        FillBounds -> ContentScale.FillBounds
        Center -> ContentScale.None
    }
}
