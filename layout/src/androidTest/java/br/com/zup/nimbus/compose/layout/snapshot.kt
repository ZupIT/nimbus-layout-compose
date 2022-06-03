package br.com.zup.nimbus.compose.layout

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.ComponentHandler
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusConfig
import br.zup.com.nimbus.compose.NimbusNavigator

val customComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "material:text" to @Composable { element, _, _ ->
        Text(text = element.properties?.get("text").toString() ?: "")
    })

private val config = NimbusConfig(
    baseUrl = "https://dummy.com",
    components = customComponents + layoutComponents
)

@Composable
fun ScreenTest(json: String) {
    AppTheme {
        Surface(color = MaterialTheme.colors.background) {
            Nimbus(config = config) {
                NimbusNavigator(json = json)
            }
        }
    }
}
