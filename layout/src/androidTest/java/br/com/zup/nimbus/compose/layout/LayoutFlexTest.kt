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
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SdkSuppress
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class LayoutFlexTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_layout_1() {
        executeScreenshotTest("layout1", composeTestRule)
    }

    @Test
    fun test_layout_2() {
        executeScreenshotTest("layout2", composeTestRule)
    }

    @Test
    fun test_layout_3() {
        executeScreenshotTest("layout3", composeTestRule)
    }

    @Test
    fun test_layout_4() {
        executeScreenshotTest("layout4", composeTestRule)
    }

    @Test
    fun test_layout_5() {
        executeScreenshotTest("layout5", composeTestRule)
    }

    @Test
    fun test_layout_6() {
        executeScreenshotTest("layout6", composeTestRule)
    }

    @Test
    fun test_layout_6_1() {
        executeScreenshotTest("layout6_1", composeTestRule)
    }

    @Test
    fun test_layout_7_1() {
        executeScreenshotTest("layout7_1", composeTestRule)
    }

    @Test
    fun test_layout_7_2() {
        executeScreenshotTest("layout7_2", composeTestRule)
    }

    @Test
    fun test_layout_7_3() {
        executeScreenshotTest("layout7_3", composeTestRule)
    }

    @Test
    fun test_layout_7_4() {
        executeScreenshotTest("layout7_4", composeTestRule)
    }

    @Test
    fun test_layout_7_5() {
        executeScreenshotTest("layout7_5", composeTestRule)
    }

    @Test
    fun test_layout_7_6() {
        executeScreenshotTest("layout7_6", composeTestRule)
    }

    @Test
    fun test_layout_8_1() {
        executeScreenshotTest("layout8_1", composeTestRule)
    }

    @Test
    fun test_layout_8_2() {
        executeScreenshotTest("layout8_2", composeTestRule)
    }

    @Test
    fun test_layout_8_3() {
        executeScreenshotTest("layout8_3", composeTestRule)
    }

    @Test
    fun test_layout_8_4() {
        executeScreenshotTest("layout8_4", composeTestRule)
    }

    @Test
    fun test_layout_9() {
        executeScreenshotTest("layout9", composeTestRule)
    }

    @Test
    fun test_layout_10() {
        executeScreenshotTest("layout10", composeTestRule)
    }

    @Test
    fun test_layout_11() {
        executeScreenshotTest("layout11", composeTestRule)
    }

    @Test
    fun test_layout_11_1() {
        executeScreenshotTest("layout11_1", composeTestRule)
    }

    @Test
    fun test_layout_13() {
        executeScreenshotTest("layout13", composeTestRule)
    }

    @Test
    fun test_layout_15() {
        executeScreenshotTest("layout15", composeTestRule)
    }

    @Test
    fun test_layout_16() {
        executeScreenshotTest("layout16", composeTestRule)
    }

    // TODO: uncomment this test as soon as we reimplement shadow deserialization
    /*@Test
    fun test_layout_19() {
        executeScreenshotTest("layout19", composeTestRule)
    }*/

    // TODO: uncomment this test as soon as we reimplement shadow deserialization
    /*@Test
    fun test_layout_19_1() {
        executeScreenshotTest("layout19_1", composeTestRule)
    }*/

    @Test
    fun test_layout_21() {
        executeScreenshotTest("layout21", composeTestRule)
    }

    @Test
    fun test_layout_22() {
        executeScreenshotTest("layout22", composeTestRule)
    }
}
