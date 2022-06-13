package br.com.zup.nimbus.compose.layout.model

import androidx.compose.ui.Alignment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal class Positioned (
    //Alignment for child components
    val alignment: PositionedAlignment? = PositionedAlignment.TOP_START,
    //Overrides parent alignment
    val alignmentSelf: PositionedAlignment? = alignment,
    val x: Double? = null,
    val y: Double? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val borderWidth: Double? = null,
    override val borderDashLength: Double? = null,
    override val borderDashSpacing: Double? = null,
    override val borderColor: String? = null,
    override val cornerRadius: Double? = null,
    override val margin: Double? = null,
    override val marginStart: Double? = null,
    override val marginEnd: Double? = null,
    override val marginTop: Double? = null,
    override val marginBottom: Double? = null,
    override val marginHorizontal: Double? = null,
    override val marginVertical: Double? = null,
    override val padding: Double? = null,
    override val paddingStart: Double? = null,
    override val paddingEnd: Double? = null,
    override val paddingTop: Double? = null,
    override val paddingBottom: Double? = null,
    override val paddingHorizontal: Double? = null,
    override val paddingVertical: Double? = null,
    override val backgroundColor: String? = null,
    override val shadow: List<Shadow>? = null,
    override val children: Component? = null
) : Box

internal enum class PositionedAlignment {
    @JsonProperty("topStart")
    TOP_START,
    @JsonProperty("topEnd")
    TOP_END,
    @JsonProperty("bottomStart")
    BOTTOM_START,
    @JsonProperty("bottomEnd")
    BOTTOM_END,
    @JsonProperty("topCenter")
    TOP_CENTER,
    @JsonProperty("bottomCenter")
    BOTTOM_CENTER,
    @JsonProperty("centerStart")
    CENTER_START,
    @JsonProperty("centerEnd")
    CENTER_END,
    @JsonProperty("center")
    CENTER;

    fun toAlignment(): Alignment =
        when (this) {
            TOP_START -> Alignment.TopStart
            TOP_END -> Alignment.TopEnd
            BOTTOM_START -> Alignment.BottomStart
            BOTTOM_END -> Alignment.BottomEnd
            TOP_CENTER -> Alignment.TopCenter
            BOTTOM_CENTER -> Alignment.BottomCenter
            CENTER_START -> Alignment.CenterStart
            CENTER_END -> Alignment.CenterEnd
            CENTER -> Alignment.Center
        }
}
