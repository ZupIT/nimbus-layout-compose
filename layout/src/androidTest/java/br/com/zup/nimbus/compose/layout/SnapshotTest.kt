package br.com.zup.nimbus.compose.layout

import android.os.Build
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.filters.SdkSuppress
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class SnapshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_1() {
        composeTestRule.setContent {
            ScreenTest(jsonFactory["1"] ?: "")
        }
        composeTestRule.waitUntilDoesNotExist(hasTestTag(loadingTag))
        compareScreenshot(composeTestRule)
    }

    @Test
    fun test_2() {
        composeTestRule.setContent {
            ScreenTest(jsonFactory["2"] ?: "")
        }
        composeTestRule.waitUntilDoesNotExist(hasTestTag(loadingTag))
        compareScreenshot(composeTestRule)
    }

}
