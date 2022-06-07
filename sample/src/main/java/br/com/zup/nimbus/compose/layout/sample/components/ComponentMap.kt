package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.sample.model.NimbusTextModel
import br.zup.com.nimbus.compose.ComponentHandler
import com.fasterxml.jackson.core.type.TypeReference

val customComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "material:text" to @Composable { element, _ , _ ->
        (element.properties)?.parse(object : TypeReference<NimbusTextModel>() {})
            ?.let { NimbusText(it) }
    },
    "material:button" to @Composable { element, _ , _ ->
        // can't use jackson to deserialize this, it has a function.
        NimbusButton(
            text = element.properties?.get("text") as String,
            onPress = element.properties!!["onPress"] as (Any?) -> Unit,
        )
    },
)
