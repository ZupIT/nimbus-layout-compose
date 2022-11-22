package br.com.zup.nimbus.compose.layout

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import br.com.zup.nimbus.android.layout.BuildConfig
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusNavigator
import br.zup.com.nimbus.compose.ProvideNimbus
import com.karumi.shot.ScreenshotTest
import java.io.InputStream
import java.util.Scanner

const val LOADING_TAG = "loadingTag"

private val nimbus = Nimbus(
    baseUrl = BASE_URL,
    ui = listOf(layoutUI),
    logger = ExceptionLogger(),
    loadingView = {
        androidx.compose.material.Text("Loading...", Modifier.semantics { testTag = LOADING_TAG })
    }
)

@Composable
fun ScreenTest(json: String) {
    AppTheme {
        Surface(color = MaterialTheme.colors.background) {
            ProvideNimbus(nimbus.imageProvider(DefaultImageProvider())) {
                NimbusNavigator(json = json)
            }
        }
    }
}

private const val WAIT_UNTIL_TIMEOUT = 60_000L
private const val WAIT_TIME_IN_MILLIS = 10L
private const val ADVANCE_TIME_IN_MILLIS = 35L
private const val LOADING_TIMEOUT_MILLIS = 500L

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT,
    advanceTime: Long = ADVANCE_TIME_IN_MILLIS,
    waitTime: Long = WAIT_TIME_IN_MILLIS,
) : SemanticsNode {
    var semanticsNode: SemanticsNode? = null
    waitUntilCompat(timeoutMillis, advanceTime, waitTime) {
        val semanticsNodes = onAllNodes(matcher).fetchSemanticsNodes()
        val nodeFound = semanticsNodes.size == count
        if (nodeFound) {
            semanticsNode = semanticsNodes.firstOrNull()
        }
        nodeFound
    }
    return semanticsNode!!
}

fun ComposeContentTestRule.waitUntilCompat(
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT,
    advanceTime: Long = ADVANCE_TIME_IN_MILLIS,
    waitTime: Long = WAIT_TIME_IN_MILLIS,
    condition: () -> Boolean,
) {
    val startTime = System.nanoTime()
    while (!condition()) {
        mainClock.advanceTimeBy(advanceTime)
        // Let Android run measure, draw and in general any other async operations.
        Thread.sleep(waitTime)
        if (System.nanoTime() - startTime > timeoutMillis * 1_000_000) {
            throw ComposeTimeoutException(
                "Condition still not satisfied after $timeoutMillis ms"
            )
        }
    }
}

/**
 * Returns the node when found
 */
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
    useActivityScreenshot: Boolean = true,
    replaceInJson: Pair<String, String>? = null,
    waitMatcher: SemanticsMatcher? = null,
) {
    executeScreenshotTest(
        jsonFile = jsonFile,
        composeTestRule = composeTestRule,
        useActivityScreenshot = useActivityScreenshot,
        replaceInJson = replaceInJson?.let { listOf(it) } ?: emptyList(),
        waitMatcher = waitMatcher?.let { arrayOf(it) } ?: emptyArray()
    )
}

fun ScreenshotTest.executeScreenshotTest(
    jsonFile: String,
    composeTestRule: ComposeContentTestRule,
    useActivityScreenshot: Boolean = true,
    replaceInJson: List<Pair<String, String>> = emptyList(),
    vararg waitMatcher: SemanticsMatcher = emptyArray(),
) {

    val json = replaceJson(getJson(jsonFile), replaceInJson)

    composeTestRule.mainClock.autoAdvance = false
    composeTestRule.setContent {
        ScreenTest(json ?: throw IllegalArgumentException("Json file can't be null."))
    }

    waitFor(composeTestRule = composeTestRule, waitMatcher = waitMatcher)
    composeTestRule.mainClock.autoAdvance = true
    if (useActivityScreenshot) {
        getCurrentActivity()?.let {
            compareScreenshot(it)
        }
    } else {
        compareScreenshot(composeTestRule)
    }
}

fun SemanticsNode.isNotInWindow(): Boolean = this.positionInWindow == Offset.Zero
fun SemanticsNode.isInWindow(): Boolean = this.isNotInWindow().not()

private fun waitFor(
    composeTestRule: ComposeContentTestRule,
    vararg waitMatcher: SemanticsMatcher = emptyArray(),
) {
    /* Sometimes the state is updated too fast and the loading can't be seen, so we need a timeout
    here. If the timeout is reached, then we keep going with our test. A better solution would be to
    wait for a server driven content to appear, but we'd need to correctly tag it in the Nimbus
    Compose lib. An even better solution would be to have the ServerDrivenNode's id as something
    searchable within the test. */
    try {
        val loadingNode = composeTestRule
            .waitUntilExists(hasTestTag(LOADING_TAG), LOADING_TIMEOUT_MILLIS)
        composeTestRule.waitUntilCompat {
            loadingNode.isNotInWindow()
        }
    } catch (e: ComposeTimeoutException) {}
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