@file:Suppress("UNCHECKED_CAST")

package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_COLUMN
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_ROW
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_TOUCHABLE
import br.com.zup.nimbus.compose.layout.model.ParentComponent
import br.zup.com.nimbus.compose.ComponentHandler
import com.fasterxml.jackson.core.type.TypeReference

val layoutComponents: Map<String, @Composable ComponentHandler> = mapOf(
    NIMBUS_TOUCHABLE to @Composable { element, children, _ ->
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
    NIMBUS_ROW to @Composable { element, children, parentElement ->
        val modelParent = parentElement?.let { parse(it, object : TypeReference<ParentComponent>() {}) }
        val model = element.parse(object : TypeReference<NimbusRowApi>() {}) ?: NimbusRowApi()
        NimbusRow(model = model, parentComponent = modelParent, content = children)
    },
    NIMBUS_COLUMN to @Composable { element, children, parentElement ->
        val modelParent = parentElement?.parse(object : TypeReference<ParentComponent>() {})
        val model = element.parse(object : TypeReference<NimbusColumnApi>() {}) ?: NimbusColumnApi()
        NimbusColumn(model = model, parentComponent = modelParent, content = children)
    },
)
