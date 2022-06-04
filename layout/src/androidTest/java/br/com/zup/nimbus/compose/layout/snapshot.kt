package br.com.zup.nimbus.compose.layout

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.ComponentHandler
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusConfig
import br.zup.com.nimbus.compose.NimbusNavigator
val loadingTag = "loadingTag"
val customComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "material:text" to @Composable { element, _, _ ->
        Text(text = element.properties?.get("text").toString())
    })

private val config = NimbusConfig(
    baseUrl = "https://dummy.com",
    components = customComponents + layoutComponents,
    loadingView = {
        Text("Loading...", Modifier.semantics { testTag = loadingTag })
    }
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


private const val WAIT_UNTIL_TIMEOUT = 4_000L

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().size == count
    }
}

fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) = waitUntilNodeCount(matcher, 1, timeoutMillis)

fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT
) = waitUntilNodeCount(matcher, 0, timeoutMillis)
