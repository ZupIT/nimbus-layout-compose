package br.com.zup.nimbus.compose.layout.model

import androidx.compose.ui.text.font.FontWeight
import br.com.zup.nimbus.compose.layout.COLOR_BLACK
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal class TextModel(
    val text: String? = "",
    val size: Double? = 12.0,
    val weight: TextWeightEnum? = TextWeightEnum.NORMAL,
    val color: String? = COLOR_BLACK
)

internal enum class TextWeightEnum {
    @JsonProperty("thin")
    THIN,
    @JsonProperty("extraLight")
    EXTRA_LIGHT,
    @JsonProperty("light")
    LIGHT,
    @JsonProperty("normal")
    NORMAL,
    @JsonProperty("medium")
    MEDIUM,
    @JsonProperty("semiBold")
    SEMI_BOLD,
    @JsonProperty("bold")
    BOLD,
    @JsonProperty("extraBold")
    EXTRA_BOLD,
    @JsonProperty("black")
    BLACK;
    

    fun toFontWeight(): FontWeight =
        when (this) {
            THIN -> FontWeight.Thin
            EXTRA_LIGHT -> FontWeight.ExtraLight
            LIGHT -> FontWeight.Light
            NORMAL -> FontWeight.Normal
            MEDIUM -> FontWeight.Medium
            SEMI_BOLD -> FontWeight.SemiBold
            BOLD -> FontWeight.Bold
            EXTRA_BOLD -> FontWeight.ExtraBold
            BLACK -> FontWeight.Black
        }
}
