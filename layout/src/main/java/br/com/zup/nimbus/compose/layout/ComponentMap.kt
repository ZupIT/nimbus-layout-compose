@file:Suppress("UNCHECKED_CAST")
package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.zup.com.nimbus.compose.ComponentHandler
import com.fasterxml.jackson.core.type.TypeReference

val layoutComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "layout:touchable" to @Composable { element, children ->
        Touchable(
            onPress = element.properties!!["onPress"] as (Any?) -> Unit,
            children = children,
            accessibility = if (element.properties!!.containsKey("accessibility")) {
                (element.properties!!["accessibility"] as Map<String, Any?>)
                    .parse(object : TypeReference<Accessibility>() {})
            } else null
        )
    },
)
