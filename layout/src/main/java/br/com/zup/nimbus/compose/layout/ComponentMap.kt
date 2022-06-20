@file:Suppress("UNCHECKED_CAST")

package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_COLUMN
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_LOCAL_IMAGE
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_POSITIONED
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_REMOTE_IMAGE
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_ROW
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_SCREEN
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_SCROLL_VIEW
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_STACK
import br.com.zup.nimbus.compose.layout.model.ComponentNames.NIMBUS_TOUCHABLE
import br.com.zup.nimbus.compose.layout.model.GenericComponentApi
import br.com.zup.nimbus.compose.layout.model.LocalImageApi
import br.com.zup.nimbus.compose.layout.model.NimbusColumnApi
import br.com.zup.nimbus.compose.layout.model.NimbusPositionedApi
import br.com.zup.nimbus.compose.layout.model.NimbusRowApi
import br.com.zup.nimbus.compose.layout.model.NimbusStackApi
import br.com.zup.nimbus.compose.layout.model.RemoteImageApi
import br.com.zup.nimbus.compose.layout.model.ScreenApi
import br.com.zup.nimbus.compose.layout.model.ScrollViewApi
import br.com.zup.nimbus.compose.layout.model.TouchableApi
import br.zup.com.nimbus.compose.ComponentHandler
import com.fasterxml.jackson.core.type.TypeReference

val layoutComponents: Map<String, @Composable ComponentHandler> = mapOf(
    NIMBUS_TOUCHABLE to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<TouchableApi>() {})
        NimbusTouchable(model = model,
            onPress = element.properties!!["onPress"] as (Any?) -> Unit,
        content = children)
    },
    NIMBUS_ROW to @Composable { element, children, parentElement ->
        val modelParent = parentElement?.let { parse(it, object : TypeReference<GenericComponentApi>() {}) }
        val model = element.parse(object : TypeReference<NimbusRowApi>() {})
        NimbusRow(model = model, parentComponent = modelParent, content = children)
    },
    NIMBUS_COLUMN to @Composable { element, children, parentElement ->
        val modelParent = parentElement?.parse(object : TypeReference<GenericComponentApi>() {})
        val model = element.parse(object : TypeReference<NimbusColumnApi>() {})
        NimbusColumn(model = model, parentComponent = modelParent, content = children)
    },
    NIMBUS_POSITIONED to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<NimbusPositionedApi>() {})
        NimbusPositioned(model = model, content = children)
    },
    NIMBUS_STACK to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<NimbusStackApi>() {})
        NimbusStack(model = model, content = children)
    },
    NIMBUS_LOCAL_IMAGE to @Composable { element, _, _ ->
        val model = element.parse(object : TypeReference<LocalImageApi>() {})
        NimbusLocalImage(model = model)
    },
    NIMBUS_REMOTE_IMAGE to @Composable { element, _, _ ->
        val model = element.parse(object : TypeReference<RemoteImageApi>() {})
        NimbusRemoteImage(model = model)
    },
    NIMBUS_SCROLL_VIEW to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<ScrollViewApi>() {})
        NimbusScrollView(model = model, content = children)
    },
    NIMBUS_SCREEN to @Composable { element, children, _ ->
        val model = element.parse(object : TypeReference<ScreenApi>() {})
        NimbusScreen(model = model, content = children)
    },
)
