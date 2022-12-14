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

package br.com.zup.nimbus.compose.layout.component.screen

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import br.com.zup.nimbus.compose.layout.extensions.isTrue
import br.com.zup.nimbus.compose.internal.NimbusNavHostHelper
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Ignore
import br.com.zup.nimbus.compose.Nimbus

@Composable
@AutoDeserialize
internal fun Screen(
    ignoreSafeArea: List<SafeAreaEdges>? = null,
    title: String?,
    showBackButton: Boolean?,
    @Ignore navHostHelper: NimbusNavHostHelper = Nimbus.navigatorInstance.navHostHelper,
    content: @Composable () -> Unit,
) {
    // The following line has been commented because it doesn't work unless the server driven view
    // is the only thing in the screen.
    // ConfigureSafeArea()

    Scaffold(
        topBar = {
            // We use TopAppBar from accompanist-insets-ui which allows us to provide
            // content padding matching the system bars insets.
            TopAppBar(
                title = { Text(title ?: "") },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                navigationIcon = shouldShowNavigationIcon(
                    showBackButton = !navHostHelper.isFirstScreen() &&
                            showBackButton.isTrue()
                ) { navHostHelper.pop() },
                contentPadding = WindowInsets.statusBars
                    .ignoreSafeArea(edgesToIgnore = ignoreSafeArea ?: emptyList())
                    .asPaddingValues(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { contentPadding ->
        Column(Modifier.padding(contentPadding)) {
            content()
        }
    }
}

@Composable
private fun shouldShowNavigationIcon(
    showBackButton: Boolean? = true,
    onClick: () -> Unit = {},
): @Composable (() -> Unit)? {
    return if (showBackButton.isTrue()) {
        { NavigationIcon(onClick = onClick) }
    } else {
        null
    }
}


@Composable
internal fun NavigationIcon(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Filled.ArrowBack,
            "")
    }
}

@Composable
internal fun ConfigureSafeArea() {
    // Turn off the decor fitting system windows, which means we need to through handling
    // insets

    val activity = LocalContext.current as Activity
    WindowCompat.setDecorFitsSystemWindows(activity.window, false)

    // Update the system bars to be translucent
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }
}
