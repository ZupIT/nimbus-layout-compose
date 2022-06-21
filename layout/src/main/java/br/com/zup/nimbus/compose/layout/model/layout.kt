package br.com.zup.nimbus.compose.layout.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

const val COLOR_BLACK = "#000000"
const val COLOR_WHITE = "#FFFFFF"

typealias Component = @Composable() () -> Unit
typealias Action = (Any?) -> Unit

@JsonIgnoreProperties(ignoreUnknown = true)
internal open class GenericComponentApi(
    override val component: String? = null,
    override val properties: ParentContainer? = null,
) : ComponentStructure

internal interface ComponentStructure {
    val component: String?
    val properties: Container?
}

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

internal interface BaseImage : Size, WithAccessibility {
    val scale: ImageScale?
}

@JsonIgnoreProperties(ignoreUnknown = true)
internal class LocalImage(
    override val scale: ImageScale? = ImageScale.CENTER,
    val id: String? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val accessibility: Accessibility? = null,
) : BaseImage

@JsonIgnoreProperties(ignoreUnknown = true)
internal class RemoteImage(
    override val scale: ImageScale? = ImageScale.CENTER,
    val url: String? = null,
    val placeholder: String? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val accessibility: Accessibility? = null,
) : BaseImage

@JsonIgnoreProperties(ignoreUnknown = true)
internal class RemoteImageApi(
    val component: String? = null,
    val properties: RemoteImage? = RemoteImage(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class LocalImageApi(
    val component: String? = null,
    val properties: LocalImage? = LocalImage(),
)

internal object ComponentNames {
    const val NIMBUS_ROW = "layout:row"
    const val NIMBUS_FLOW_ROW = "layout:flowRow"
    const val NIMBUS_COLUMN = "layout:column"
    const val NIMBUS_FLOW_COLUMN = "layout:flowColumn"
    const val NIMBUS_TOUCHABLE = "layout:touchable"
    const val NIMBUS_POSITIONED = "layout:positioned"
    const val NIMBUS_STACK = "layout:stack"
    const val NIMBUS_REMOTE_IMAGE = "layout:remoteImage"
    const val NIMBUS_LOCAL_IMAGE = "layout:localImage"
    const val NIMBUS_SCROLL_VIEW = "layout:scroll"
    const val NIMBUS_SCREEN = "layout:screen"
}


@JsonIgnoreProperties(ignoreUnknown = true)
internal class TouchableModel(
    override val accessibility: Accessibility? = null,
) : WithAccessibility

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusRowApi(
    override val component: String? = null,
    override val properties: NimbusRowModel? = NimbusRowModel(),
) : ComponentStructure

@JsonIgnoreProperties(ignoreUnknown = true)
internal class TouchableApi(
    val component: String? = null,
    val properties: TouchableModel? = TouchableModel(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusPositionedApi(
    val component: String? = null,
    val properties: Positioned? = Positioned(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ScrollViewApi(
    val component: String? = null,
    val properties: ScrollView? = ScrollView(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ScreenApi(
    val component: String? = null,
    val properties: Screen? = Screen(),
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusColumnApi(
    override val component: String? = null,
    override val properties: NimbusColumnModel? = NimbusColumnModel(),
) : ComponentStructure

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusBoxApi(
    val component: String? = null,
    val properties: BoxModel? = BoxModel(),
)

internal enum class CrossAxisAlignment {
    @JsonProperty("stretch")
    STRETCH,

    @JsonProperty("start")
    START,

    @JsonProperty("end")
    END,

    @JsonProperty("center")
    CENTER;

    fun toVerticalAlignment(): Alignment.Vertical =
        when (this) {
            STRETCH -> Alignment.Top
            START -> Alignment.Top
            END -> Alignment.Bottom
            CENTER -> Alignment.CenterVertically
        }

    fun toHorizontalAlignment(): Alignment.Horizontal =
        when (this) {
            STRETCH -> Alignment.Start
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
