package br.com.zup.nimbus.compose.layout

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import br.com.zup.nimbus.android.layout.test.BuildConfig
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.ComponentHandler
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusConfig
import br.zup.com.nimbus.compose.NimbusNavigator
import br.zup.com.nimbus.compose.VIEW_INITIAL_URL
import com.karumi.shot.ScreenshotTest
import java.io.InputStream
import java.util.Scanner

const val NIMBUS_PAGE = "NimbusPage"

const val loadingTag = "loadingTag"
val customComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "material:text" to @Composable { element, _, _ ->
        Text(text = element.properties?.get("text").toString(),
            maxLines = element.properties?.get("maxLines")?.toString()?.toInt() ?: Int.MAX_VALUE)
    })

private val config = NimbusConfig(
    baseUrl = BASE_URL,
    components = customComponents + layoutComponents,
    loadingView = {
        Text("Loading...", Modifier.semantics { testTag = loadingTag })
    }
)

@Composable
fun ScreenTest(json: String) {
    AppTheme {
        Surface(color = MaterialTheme.colors.background) {
            Nimbus(config = config.imageProvider(DefaultImageProvider())) {
                NimbusNavigator(json = json)
            }
        }
    }
}

private const val WAIT_UNTIL_TIMEOUT = 60_000L
private const val WAIT_TIME_IN_MILLIS = 10L
private const val ADVANCE_TIME_IN_MILLIS = 40L

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT,
    advanceTime: Long = ADVANCE_TIME_IN_MILLIS,
    waitTime: Long = WAIT_TIME_IN_MILLIS,
) {
    waitUntil(timeoutMillis, advanceTime, waitTime) {
        onAllNodes(matcher).fetchSemanticsNodes().size == count
    }
}

@SuppressWarnings("DocumentExceptions") // The interface doc already documents this
fun ComposeContentTestRule.waitUntil(
    timeoutMillis: Long,
    advanceTime: Long,
    waitTime: Long,
    condition: () -> Boolean,
) {
    val startTime = System.nanoTime()
    while (!condition()) {
        if (this.mainClock.autoAdvance) {
            mainClock.advanceTimeBy(advanceTime)
        }
        // Let Android run measure, draw and in general any other async operations.
        Thread.sleep(waitTime)
        if (System.nanoTime() - startTime > timeoutMillis * 1_000_000) {
            throw ComposeTimeoutException(
                "Condition still not satisfied after $timeoutMillis ms"
            )
        }
    }
}

fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT,
) = waitUntilNodeCount(matcher, 1, timeoutMillis)

fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT,
) = waitUntilNodeCount(matcher, 0, timeoutMillis)


fun getContext(): Context = getInstrumentation().targetContext

fun ScreenshotTest.executeScreenshotTest(
    jsonFile: String,
    composeTestRule: ComposeContentTestRule,
    screenName: String = "${NIMBUS_PAGE}:${VIEW_INITIAL_URL}",
    useActivityScreenshot: Boolean = true,
    replaceInJson: Pair<String, String>? = null,
    waitMatcher: SemanticsMatcher? = null,
) {
    executeScreenshotTest(
        jsonFile = jsonFile,
        composeTestRule = composeTestRule,
        screenName = screenName,
        useActivityScreenshot = useActivityScreenshot,
        replaceInJson = replaceInJson?.let { listOf(it) } ?: emptyList(),
        waitMatcher = waitMatcher?.let { arrayOf(it) } ?: emptyArray()
    )
}

fun ScreenshotTest.executeScreenshotTest(
    jsonFile: String,
    composeTestRule: ComposeContentTestRule,
    screenName: String = "${NIMBUS_PAGE}:${VIEW_INITIAL_URL}",
    useActivityScreenshot: Boolean = true,
    replaceInJson: List<Pair<String, String>> = emptyList(),
    vararg waitMatcher: SemanticsMatcher = emptyArray(),
) {

    val json = replaceJson(getJson(jsonFile), replaceInJson)

    composeTestRule.setContent {
        ScreenTest(json ?: "")
    }

    waitFor(composeTestRule = composeTestRule, screenName = screenName, waitMatcher = waitMatcher)
    if (useActivityScreenshot) {
        getCurrentActivity()?.let {
            compareScreenshot(it)
        }
    } else {
        compareScreenshot(composeTestRule)
    }
}

private fun waitFor(
    composeTestRule: ComposeContentTestRule,
    screenName: String,
    vararg waitMatcher: SemanticsMatcher = emptyArray(),
) {
    composeTestRule.waitUntilExists(hasTestTag(screenName))
    waitMatcher.forEach {
        composeTestRule.waitUntilExists(it)
    }
}

private fun replaceJson(json: String?, replaceInJson: List<Pair<String, String>>): String? {
    return json?.let { jsonString ->
        var tempJson = jsonString
        replaceInJson.forEach {
            tempJson = tempJson.replace(it.first, it.second)
        }

        return tempJson
    }
}

fun getCurrentActivity(): Activity? {
    val currentActivity = arrayOf<Activity?>(null)
    getInstrumentation().runOnMainSync {
        val resumedActivities: Collection<*> = ActivityLifecycleMonitorRegistry.getInstance()
            .getActivitiesInStage(Stage.RESUMED)
        if (resumedActivities.iterator().hasNext()) {
            currentActivity[0] = resumedActivities.iterator().next() as Activity?
        }
    }
    return currentActivity[0]
}

fun Context.readRawResource(@RawRes res: Int): String? {
    return readStream(resources.openRawResource(res))
}

fun Context.readImageAsBytes(@DrawableRes res: Int): ByteArray {
    return resources.openRawResource(res).readBytes()
}

fun ScreenshotTest.getJson(jsonFile: String): String? {
    return getContext().readRawResource(
        getIdentifierResourceByName(getContext().resources,
            "raw/$jsonFile"))
}

private fun getIdentifierResourceByName(resources: Resources, aString: String): Int =
    resources.getIdentifier(aString, "string", BuildConfig.APPLICATION_ID)

private fun readStream(inputStream: InputStream): String? {
    val s: Scanner = Scanner(inputStream).useDelimiter("\\A")
    return if (s.hasNext()) s.next() else ""
}