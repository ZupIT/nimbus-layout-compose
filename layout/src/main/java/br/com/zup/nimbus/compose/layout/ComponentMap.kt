package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.zup.com.nimbus.compose.ComponentHandler
import com.zup.nimbus.core.tree.ServerDrivenNode

val layoutComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "layout:touchable" to @Composable { element, children ->
        Touchable(
            onPress = element.properties!!["onPress"] as (Any?) -> Unit,
            children = children,
            accessibility = getAccessibility(element)
        )
    },
)

private fun getAccessibility(element: ServerDrivenNode): Accessibility? =
    if (element.properties!!.containsKey("accessibility")) {
        val accessibility = element.properties!!["accessibility"] as Map<*, *>
        Accessibility(
            label = accessibility["label"] as String?,
            isHeader = accessibility["isHeader"] as Boolean?
        )
    } else null
