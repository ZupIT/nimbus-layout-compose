/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    private val waitSemanticMatcher = hasContentDescription(contentDescriptionImage)

    @Test
    fun test_image_center() {
        val scale = ImageScale.Center.toString()
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
        waitMatcher = waitSemanticMatcher,
        replaceInJson = scaleReplace to scale)
    }

    @Test
    fun test_image_fill_bounds() {
        val scale = ImageScale.FillBounds.toString()
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            waitMatcher = waitSemanticMatcher,
            replaceInJson = scaleReplace to scale)
    }

    @Test
    fun test_image_fill_height() {
        val scale = ImageScale.FillHeight.toString()
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            waitMatcher = waitSemanticMatcher,
            replaceInJson = scaleReplace to scale)
    }

    @Test
    fun test_image_fill_width() {
        val scale = ImageScale.FillWidth.toString()
        executeScreenshotTest(jsonFile = jsonFileName,
            composeTestRule = composeTestRule,
            waitMatcher = waitSemanticMatcher,
            replaceInJson = scaleReplace to scale)
    }
}
