package br.com.zup.nimbus.compose.layout.model

import androidx.compose.foundation.layout.WindowInsetsSides
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal class Screen(
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

private val AllWindowInsetsSides =
    WindowInsetsSides.Left +
            (WindowInsetsSides.Top) +
            (WindowInsetsSides.Right) +
            (WindowInsetsSides.Bottom)
/**
 * Transform the list of of SafeAreaEdges that must be ignored into WindowInsetsSides
 * that will have all WindowInsetsSides except the ones that must be ignored.
 */
internal fun List<SafeAreaEdges>.toWindowInsetsSidesOnly(): WindowInsetsSides {
    var sides: WindowInsetsSides? = null
    if (this.isEmpty()) {
        return AllWindowInsetsSides
    } else {
        val allEdges = mutableListOf(
            SafeAreaEdges.TOP,
            SafeAreaEdges.LEADING,
            SafeAreaEdges.TRAILING,
            SafeAreaEdges.BOTTOM)

        this.forEach {
            allEdges.remove(it)
        }

        allEdges.forEach { currentEdge ->
            sides = sides?.let { it + currentEdge.toWindowInsetsSide() } ?: currentEdge.toWindowInsetsSide()
        }
    }
    return sides ?: WindowInsetsSides.Bottom
}
