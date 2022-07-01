package br.com.zup.nimbus.compose.layout.model

import androidx.compose.ui.Alignment
import com.fasterxml.jackson.annotation.JsonProperty

internal enum class CrossAxisAlignment {

    @JsonProperty("start")
    START,

    @JsonProperty("end")
    END,

    @JsonProperty("center")
    CENTER;

    fun toVerticalAlignment(): Alignment.Vertical =
        when (this) {
            START -> Alignment.Top
            END -> Alignment.Bottom
            CENTER -> Alignment.CenterVertically
        }

    fun toHorizontalAlignment(): Alignment.Horizontal =
        when (this) {
            START -> Alignment.Start
            END -> Alignment.End
            CENTER -> Alignment.CenterHorizontally
        }
}
