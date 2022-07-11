package br.com.zup.nimbus.compose.layout.model

import androidx.compose.foundation.layout.WindowInsetsSides
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ScreenModel(
    val ignoreSafeArea: List<SafeAreaEdges> = emptyList(),
    val title: String? = null,
    val showBackButton: Boolean? = true,
    // navigationBarItems?: { title: string, image: string }[],
    val children: Component? = null,
)


internal enum class SafeAreaEdges {
    @JsonProperty("top")
    TOP,

    @JsonProperty("bottom")
    BOTTOM,

    @JsonProperty("trailing")
    TRAILING,

    @JsonProperty("leading")
    LEADING;

    fun toWindowInsetsSide(): WindowInsetsSides =
        when (this) {
            TOP-> WindowInsetsSides.Top
            BOTTOM -> WindowInsetsSides.Bottom
            TRAILING -> WindowInsetsSides.Right
            LEADING -> WindowInsetsSides.Left
        }
}
