package br.com.zup.nimbus.compose.layout

import android.os.Build
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SdkSuppress
import br.com.zup.nimbus.compose.layout.model.ImageScale
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class LocalImageTest : ScreenshotTest {

    private val contentDescriptionImage = "This is the golden nimbus"

    @get:Rule
    val composeTestRule = createComposeRule()

    private val jsonFileName = "local_image"

    private val SCALE_REPLACE = "@(scale)"

    private val waitSemanticMatcher = hasContentDescription(contentDescriptionImage)

    @Test
    fun test_image_center() {
        val scale = ImageScale.CENTER.value
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
        waitMatcher = waitSemanticMatcher,
        replaceInJson = listOf(SCALE_REPLACE to scale))
    }

    @Test
    fun test_image_fill_bounds() {
        val scale = ImageScale.FILL_BOUNDS.value
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            waitMatcher = waitSemanticMatcher,
            replaceInJson = listOf(SCALE_REPLACE to scale))
    }

    @Test
    fun test_image_fill_height() {
        val scale = ImageScale.FILL_HEIGHT.value
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            waitMatcher = waitSemanticMatcher,
            replaceInJson = listOf(SCALE_REPLACE to scale))
    }

    @Test
    fun test_image_fill_width() {
        val scale = ImageScale.FILL_WIDTH.value
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            waitMatcher = waitSemanticMatcher,
            replaceInJson = listOf(SCALE_REPLACE to scale))
    }
}
