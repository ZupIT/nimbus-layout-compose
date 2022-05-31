@file:Suppress("UNCHECKED_CAST")
package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.zup.com.nimbus.compose.ComponentHandler
import com.fasterxml.jackson.core.type.TypeReference

val layoutComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "layout:touchable" to @Composable { element, children ->
        val model = TouchableModel(
            onPress = element.properties!!["onPress"] as (Any?) -> Unit,
            children = children,
            accessibility = if (element.properties!!.containsKey("accessibility")) {
                (element.properties!!["accessibility"] as Map<String, Any?>)
                    .parse(object : TypeReference<Accessibility>() {})
            } else null
        )
        NimbusTouchable(model)
    },
    "layout:row" to @Composable { element, children ->
        val model = element.parse(object : TypeReference<RowModel>() {}) ?: RowModel()
        NimbusRow(model = model, content = children)
    },
    "layout:column" to @Composable { element, children ->
        val model = element.parse(object : TypeReference<ColumnModel>() {}) ?: ColumnModel()
        NimbusColumn(model = model, content = children)
    },
)
