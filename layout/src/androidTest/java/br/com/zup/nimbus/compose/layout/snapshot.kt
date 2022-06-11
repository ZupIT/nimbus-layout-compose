package br.com.zup.nimbus.compose.layout

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.view.View
import androidx.annotation.RawRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.core.graphics.applyCanvas
import androidx.core.view.ViewCompat
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import br.com.zup.nimbus.android.layout.test.BuildConfig
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.zup.com.nimbus.compose.ComponentHandler
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusConfig
import br.zup.com.nimbus.compose.NimbusNavigator
import br.zup.com.nimbus.compose.VIEW_INITIAL_URL
import com.karumi.shot.ScreenshotTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.io.InputStream
import java.util.Scanner


const val NIMBUS_PAGE = "NimbusPage"

val loadingTag = "loadingTag"
val customComponents: Map<String, @Composable ComponentHandler> = mapOf(
    "material:text" to @Composable { element, _, _ ->
        Text(text = element.properties?.get("text").toString(),
            maxLines = element.properties?.get("maxLines")?.toString()?.toInt() ?: Int.MAX_VALUE)
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

private const val WAIT_UNTIL_TIMEOUT = 40_000L

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = WAIT_UNTIL_TIMEOUT,
) {
    waitUntil(timeoutMillis) {
        onAllNodes(matcher).fetchSemanticsNodes().size == count
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


fun ScreenshotTest.getContext(): Context = getInstrumentation().targetContext

@OptIn(ExperimentalCoroutinesApi::class)
fun ScreenshotTest.executeScreenshotTest(
    jsonFile: String, composeTestRule: ComposeContentTestRule,
    screenName: String = "${NIMBUS_PAGE}:${VIEW_INITIAL_URL}",
    useActivityScreenshot: Boolean = true,
)= runTest {
    composeTestRule.setContent {
        ScreenTest(getJson(jsonFile) ?: "")
    }

    composeTestRule.waitUntilDoesNotExist(hasTestTag(loadingTag))
    composeTestRule.waitUntilExists(hasTestTag(screenName))
    composeTestRule.mainClock.autoAdvance = false
    if (useActivityScreenshot) {
        getCurrentActivity()?.let {
            compareScreenshot(it)
        }
    } else {
        compareScreenshot(composeTestRule)
    }
    composeTestRule.mainClock.autoAdvance = true
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

/**
 * Return a [Bitmap] representation of this [View].
 *
 * The resulting bitmap will be the same width and height as this view's current layout
 * dimensions. This does not take into account any transformations such as scale or translation.
 *
 * Note, this will use the software rendering pipeline to draw the view to the bitmap. This may
 * result with different drawing to what is rendered on a hardware accelerated canvas (such as
 * the device screen).
 *
 * If this view has not been laid out this method will throw a [IllegalStateException].
 *
 * @param config Bitmap config of the desired bitmap. Defaults to [Bitmap.Config.ARGB_8888].
 */
fun View.drawToBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    if (!ViewCompat.isLaidOut(this)) {
        throw IllegalStateException("View needs to be laid out before calling drawToBitmap()")
    }
    return Bitmap.createBitmap(width, height, config).applyCanvas {
        translate(-scrollX.toFloat(), -scrollY.toFloat())
        draw(this)
    }
}

fun Context.readRawResource(@RawRes res: Int): String? {
    return readStream(resources.openRawResource(res))
}

fun ScreenshotTest.getJson(jsonFile: String): String? {
    return getContext().readRawResource(
        getIdentifierResourceByName(getContext().resources,
            "raw/$jsonFile"))
}

private fun getIdentifierResourceByName(resources: Resources,aString: String): Int =
    resources.getIdentifier(aString, "string", BuildConfig.APPLICATION_ID)

private fun readStream(inputStream: InputStream): String? {
    val s: Scanner = Scanner(inputStream).useDelimiter("\\A")
    return if (s.hasNext()) s.next() else ""
}