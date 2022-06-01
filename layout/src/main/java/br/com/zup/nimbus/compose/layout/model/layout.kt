package br.com.zup.nimbus.compose.layout.model

import androidx.compose.runtime.Composable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

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
    override val properties: NimbusRowModel? = null
): ComponentStructure

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusColumnModel : Container()

@JsonIgnoreProperties(ignoreUnknown = true)
internal class NimbusColumnApi(
    override val component: String? = null,
    override val properties: NimbusColumnModel? = null
): ComponentStructure
