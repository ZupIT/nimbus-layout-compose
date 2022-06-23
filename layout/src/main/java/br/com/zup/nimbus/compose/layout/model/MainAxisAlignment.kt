package br.com.zup.nimbus.compose.layout.model

import androidx.compose.foundation.layout.Arrangement
import com.fasterxml.jackson.annotation.JsonProperty

internal enum class MainAxisAlignment {
    @JsonProperty("start")
    START,

    @JsonProperty("end")
    END,

    @JsonProperty("center")
    CENTER,

    @JsonProperty("spaceBetween")
    SPACE_BETWEEN,

    @JsonProperty("spaceAround")
    SPACE_AROUND,

    @JsonProperty("spaceEvenly")
    SPACE_EVENLY;

    fun toHorizontalArrangement(): Arrangement.Horizontal =
        when (this) {
            START -> Arrangement.Start
            END -> Arrangement.End
            CENTER -> Arrangement.Center
            SPACE_BETWEEN -> Arrangement.SpaceBetween
            SPACE_AROUND -> Arrangement.SpaceAround
            SPACE_EVENLY -> Arrangement.SpaceEvenly
        }

    fun toVerticalArrangement(): Arrangement.Vertical =
        when (this) {
            START -> Arrangement.Top
            END -> Arrangement.Bottom
            CENTER -> Arrangement.Center
            SPACE_BETWEEN -> Arrangement.SpaceBetween
            SPACE_AROUND -> Arrangement.SpaceAround
            SPACE_EVENLY -> Arrangement.SpaceEvenly
        }
}
