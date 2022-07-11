@file:Suppress("UNCHECKED_CAST")

package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.component.Column
import br.com.zup.nimbus.compose.layout.component.FlowColumn
import br.com.zup.nimbus.compose.layout.component.FlowRow
import br.com.zup.nimbus.compose.layout.component.Lifecycle
import br.com.zup.nimbus.compose.layout.component.LocalImage
import br.com.zup.nimbus.compose.layout.component.Positioned
import br.com.zup.nimbus.compose.layout.component.RemoteImage
import br.com.zup.nimbus.compose.layout.component.Row
import br.com.zup.nimbus.compose.layout.component.Screen
import br.com.zup.nimbus.compose.layout.component.ScrollView
import br.com.zup.nimbus.compose.layout.component.Stack
import br.com.zup.nimbus.compose.layout.component.Text
import br.com.zup.nimbus.compose.layout.component.Touchable
import br.com.zup.nimbus.compose.layout.extensions.parse
import br.com.zup.nimbus.compose.layout.model.Accessibility
import br.com.zup.nimbus.compose.layout.model.BoxModel
import br.com.zup.nimbus.compose.layout.model.ColumnModel
import br.com.zup.nimbus.compose.layout.model.WithChildStretch
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_COLUMN
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_LIFECYCLE
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_FLOW_COLUMN
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_FLOW_ROW
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_LOCAL_IMAGE
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_POSITIONED
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_REMOTE_IMAGE
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_ROW
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_SCREEN
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_SCROLL_VIEW
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_STACK
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_TEXT
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_TOUCHABLE
import br.com.zup.nimbus.compose.layout.model.LocalImageModel
import br.com.zup.nimbus.compose.layout.model.PositionedModel
import br.com.zup.nimbus.compose.layout.model.RemoteImageModel
import br.com.zup.nimbus.compose.layout.model.RowModel
import br.com.zup.nimbus.compose.layout.model.ScreenModel
import br.com.zup.nimbus.compose.layout.model.ScrollViewModel
import br.com.zup.nimbus.compose.layout.model.TextModel
import br.com.zup.nimbus.compose.layout.model.TouchableModel
import br.com.zup.nimbus.compose.layout.model.WithAccessibility
import br.zup.com.nimbus.compose.ComponentHandler
import com.fasterxml.jackson.core.type.TypeReference
import com.zup.nimbus.core.tree.ServerDrivenNode

val layoutComponents: Map<String, @Composable ComponentHandler> = mapOf(
    NIMBUS_TOUCHABLE to @Composable { element, children, _ ->
        val model = TouchableModel(
            accessibility = element.properties?.get("accessibility")?.let {
                parse(it, object : TypeReference<Accessibility>() {})
            },
            // fix "!!" when deciding the final deserialization process
            onPress = element.properties!!["onPress"] as (Any?) -> Unit
        )
        Touchable(model = model, content = children)
    },
    NIMBUS_ROW to @Composable { element, children, parentElement ->
        val parentComponentName = parentElement?.component
        val model = element.parse(object : TypeReference<RowModel>() {}) ?: RowModel()
        parseStretch(model, element)
        Row(model = model, parentComponentName = parentComponentName, content = children)
    },
    NIMBUS_FLOW_ROW to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<BoxModel>() {}) ?: BoxModel()
        FlowRow(model = model, content = children)
    },
    NIMBUS_COLUMN to @Composable { element, children, parentElement ->
        val parentComponentName = parentElement?.component
        val model = element.parse(object : TypeReference<ColumnModel>() {}) ?: ColumnModel()
        parseStretch(model, element)
        Column(model = model, parentComponentName = parentComponentName, content = children)
    },
    NIMBUS_FLOW_COLUMN to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<BoxModel>() {}) ?: BoxModel()
        FlowColumn(model = model, content = children)
    },
    NIMBUS_POSITIONED to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<PositionedModel>() {}) ?: PositionedModel()
        Positioned(model = model, content = children)
    },
    NIMBUS_STACK to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<BoxModel>() {}) ?: BoxModel()
        Stack(model = model, content = children)
    },
    NIMBUS_LOCAL_IMAGE to @Composable { element, _, _ ->
        val model = element.parse(object : TypeReference<LocalImageModel>() {}) ?: LocalImageModel()
        LocalImage(model = model)
    },
    NIMBUS_REMOTE_IMAGE to @Composable { element, _, _ ->
        val model = element.parse(object : TypeReference<RemoteImageModel>() {})
        if (model != null) RemoteImage(model = model)
    },
    NIMBUS_SCROLL_VIEW to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<ScrollViewModel>() {}) ?: ScrollViewModel()
        ScrollView(model = model, content = children)
    },
    NIMBUS_LIFECYCLE to @Composable { element, children, _ ->
        // can't use jackson to deserialize this, it has a function.
        Lifecycle(
            onInit = element.properties?.get("onInit") as? (Any?) -> Unit,
            content = children
        )
    },
    NIMBUS_SCREEN to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<ScreenModel>() {}) ?: ScreenModel()
        Screen(model = model, content = children)
    },
    NIMBUS_TEXT to @Composable { element, _, _ ->
        val model = element.parse(object : TypeReference<TextModel>() {})
        if (model != null) Text(model = model)
    },
)

private fun parseStretch(
    model: WithChildStretch?,
    element: ServerDrivenNode,
) {
    model?.hasChildStretch =
        element.children?.any { it.properties?.get("stretch") == true } ?: false
}
