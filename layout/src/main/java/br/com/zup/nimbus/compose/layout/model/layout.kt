package br.com.zup.nimbus.compose.layout.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

const val COLOR_BLACK = "#000000"

typealias Component = @Composable() () -> Unit
typealias Action = (Any?) -> Unit

@JsonIgnoreProperties(ignoreUnknown = true)
internal open class GenericComponentApi(
    override val component: String? = null,
    override val properties: Container? = null
): ComponentStructure

internal interface ComponentStructure {
    val component: String?
    val properties: Container?
}

internal object ComponentNames {
    const val NIMBUS_ROW = "layout:row"
    const val NIMBUS_COLUMN = "layout:column"
    const val NIMBUS_TOUCHABLE = "layout:touchable"
}

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusRowModel : Container()

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusRowApi(
    override val component: String? = null,
    override val properties: NimbusRowModel? = NimbusRowModel()
): ComponentStructure

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusColumnModel : Container()

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusColumnApi(
    override val component: String? = null,
    override val properties: NimbusColumnModel? = NimbusColumnModel()
): ComponentStructure

internal enum class CrossAxisAlignment {
    @JsonProperty("stretch")
    STRETCH,
    @JsonProperty("start")
    START,
    @JsonProperty("end")
    END,
    @JsonProperty("center")
    CENTER;

    fun toVerticalAlignment(): Alignment.Vertical? =
        when (this) {
            STRETCH -> null
            START -> Alignment.Top
            END -> Alignment.Bottom
            CENTER -> Alignment.CenterVertically
        }

    fun toHorizontalAlignment(): Alignment.Horizontal? =
        when (this) {
            STRETCH -> null
            START -> Alignment.Start
            END -> Alignment.End
            CENTER -> Alignment.CenterHorizontally
        }
}

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
