package br.com.zup.nimbus.compose.layout.model

import androidx.compose.runtime.Composable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

const val COLOR_BLACK = "#000000"

typealias Component = @Composable() () -> Unit
typealias Action = (Any?) -> Unit

@JsonIgnoreProperties(ignoreUnknown = true)
internal class LayoutComponent(
    val component: String? = null,
    val properties: Container? = null
)

internal object ComponentNames {
    const val NIMBUS_ROW = "layout:row"
    const val NIMBUS_COLUMN = "layout:column"
    const val NIMBUS_TOUCHABLE = "layout:touchable"
}
