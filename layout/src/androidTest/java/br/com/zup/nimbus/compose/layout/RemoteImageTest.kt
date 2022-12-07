package br.com.zup.nimbus.compose.layout

import android.os.Build
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SdkSuppress
import br.com.zup.nimbus.compose.layout.component.image.ImageScale
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class RemoteImageTest : ScreenshotTest {

    private val contentDescriptionImage = "This is the golden nimbus"

    @get:Rule
    val composeTestRule = createComposeRule()

    private val jsonFileName = "remote_image"

    private val scaleReplace = "@(scale)"

    private val waitForImage = {
        composeTestRule.waitForElementWithDescriptionToBeVisible(contentDescriptionImage)
    }

    @Test
    fun test_image_center() {
        val scale = ImageScale.Center.toString()
        executeScreenshotTest(
            jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            replaceInJson = scaleReplace to scale,
            afterScreenRendered = waitForImage,
        )
    }

    @Test
    fun test_image_fill_bounds() {
        val scale = ImageScale.FillBounds.toString()
        executeScreenshotTest(
            jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            replaceInJson = scaleReplace to scale,
            afterScreenRendered = waitForImage,
        )
    }

    @Test
    fun test_image_fill_height() {
        val scale = ImageScale.FillHeight.toString()
        executeScreenshotTest(
            jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            replaceInJson = scaleReplace to scale,
            afterScreenRendered = waitForImage,
        )
    }

    @Test
    fun test_image_fill_width() {
        val scale = ImageScale.FillWidth.toString()
        executeScreenshotTest(
            jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            replaceInJson = scaleReplace to scale,
            afterScreenRendered = waitForImage,
        )
    }
}
