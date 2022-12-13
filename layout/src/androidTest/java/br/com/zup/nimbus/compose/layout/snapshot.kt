package br.com.zup.nimbus.compose.layout

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import br.com.zup.nimbus.android.layout.BuildConfig
import br.com.zup.nimbus.compose.layout.extensions.imageProvider
import br.com.zup.nimbus.compose.layout.sample.theme.AppTheme
import br.com.zup.nimbus.compose.Nimbus
import br.com.zup.nimbus.compose.NimbusNavigator
import br.com.zup.nimbus.compose.ProvideNimbus
import br.com.zup.nimbus.compose.ui.NimbusComposeUILibrary
import br.com.zup.nimbus.compose.ui.composeUILibrary
import com.karumi.shot.ScreenshotTest
import java.io.InputStream
import java.util.Scanner

const val ROOT_NODE_ID = "nimbus:root"

private val nimbus = Nimbus(
    baseUrl = BASE_URL,
    ui = listOf(layoutUI),
    logger = ExceptionLogger(),
).run {
    // overrides the component "fragment" in the core UI to include the id as a testTag
    (composeUILibrary as NimbusComposeUILibrary).addComponent("fragment") @Composable {
        Column(Modifier.semantics { testTag = it.node.id }) {
            it.children()
        }
    }
    this
}

@Composable
fun ScreenTest(json: String) {
    AppTheme {
        Surface(color = MaterialTheme.colors.background) {
            ProvideNimbus(nimbus.imageProvider(TestImageProvider())) {
                NimbusNavigator(json = json)
            }
        }
    }
}

fun getContext(): Context = getInstrumentation().targetContext

fun ComposeContentTestRule.waitForElementWithTagToBeVisible(testTag: String) = this.waitUntil {
    this.onAllNodesWithTag(testTag).fetchSemanticsNodes().isNotEmpty()
}

fun ComposeContentTestRule.waitForElementWithDescriptionToBeVisible(
    description: String,
    timeoutMillis: Long = 1_000,
) = this.waitUntil(timeoutMillis) {
    this.onAllNodesWithContentDescription(description).fetchSemanticsNodes().isNotEmpty()
}

private fun ComposeContentTestRule.waitForServerDrivenViewToBeVisible() =
    waitForElementWithTagToBeVisible(ROOT_NODE_ID)

fun ScreenshotTest.executeScreenshotTest(
    jsonFile: String,
    composeTestRule: ComposeContentTestRule,
    replaceInJson: Pair<String, String>? = null,
    afterScreenRendered: () -> Unit = {},
) {
    val json = replaceJson(
        getJson(jsonFile),
        replaceInJson?.let { listOf(it) } ?: emptyList(),
    )
    composeTestRule.setContent {
        ScreenTest(json ?: throw IllegalArgumentException("Json file can't be null."))
    }
    composeTestRule.waitForServerDrivenViewToBeVisible()
    afterScreenRendered()
    getCurrentActivity()?.let { compareScreenshot(it) }
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

fun getJson(jsonFile: String): String? {
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