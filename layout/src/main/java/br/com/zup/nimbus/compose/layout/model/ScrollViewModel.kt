package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ScrollViewModel (
    val direction: ScrollViewDirection? = ScrollViewDirection.BOTH,
    val scrollIndicator: Boolean? = true, //default: true
    val children: Component? = null
)

internal enum class ScrollViewDirection {
    @JsonProperty("both")
    BOTH,
    @JsonProperty("horizontal")
    HORIZONTAL,
    @JsonProperty("vertical")
    VERTICAL;
}
