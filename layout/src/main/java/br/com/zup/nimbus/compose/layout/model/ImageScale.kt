package br.com.zup.nimbus.compose.layout.model

import androidx.compose.ui.layout.ContentScale
import com.fasterxml.jackson.annotation.JsonProperty

internal enum class ImageScale(val value: String) {

    @JsonProperty("fillHeight")
    FILL_HEIGHT("fillHeight"), // CENTER_CROP

    @JsonProperty("fillWidth")
    FILL_WIDTH("fillWidth"), // FIT_CENTER

    @JsonProperty("fillBounds")
    FILL_BOUNDS("fillBounds"), // FIT_XY

    @JsonProperty("center")
    CENTER("center"); // CENTER


    fun toContentScale(): ContentScale =
        when (this) {
            FILL_HEIGHT -> ContentScale.Crop
            FILL_WIDTH -> ContentScale.Fit
            FILL_BOUNDS -> ContentScale.FillBounds
            CENTER -> ContentScale.None
        }
}
