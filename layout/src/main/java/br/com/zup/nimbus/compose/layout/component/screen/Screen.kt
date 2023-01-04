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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Ignore
import br.com.zup.nimbus.compose.Nimbus
import br.com.zup.nimbus.compose.internal.NimbusNavHostHelper
import br.com.zup.nimbus.compose.layout.extensions.color
import br.com.zup.nimbus.compose.layout.extensions.isTrue
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
@AutoDeserialize
internal fun Screen(
    ignoreSafeArea: List<SafeAreaEdges>? = null,
    title: String? = null,
    safeAreaTopBackground: Color? = null,
    showBackButton: Boolean? = true,
    @Ignore navHostHelper: NimbusNavHostHelper = Nimbus.navigatorInstance.navHostHelper,
    content: @Composable () -> Unit,
) {
     ConfigureSafeArea(safeAreaTopBackground)

    val canShowBackButton = !navHostHelper.isFirstScreen() && showBackButton.isTrue()
    val canShowTitle = title != null
    Scaffold(
        topBar = {
            // We use TopAppBar from accompanist-insets-ui which allows us to provide
            // content padding matching the system bars insets.
            TopAppBar(
                title = { if (canShowTitle) Text(title ?: "") },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                navigationIcon = shouldShowNavigationIcon(
                    showBackButton = canShowBackButton
                ) { navHostHelper.pop() },
                contentPadding = WindowInsets.statusBars
                    .ignoreSafeArea(edgesToIgnore = ignoreSafeArea ?: emptyList())
                    .asPaddingValues(),
                modifier = if (canShowTitle || canShowBackButton)
                    Modifier.fillMaxWidth()
                else
                    Modifier.size(0.dp)
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
internal fun ConfigureSafeArea(safeAreaTopBackground: Color?) {
    // Turn off the decor fitting system windows, which means we need to through handling
    // insets
// FIXME The following line has been commented because it doesn't work unless the server driven view
// is the only thing in the screen.
//    val activity = LocalContext.current as Activity
//    WindowCompat.setDecorFitsSystemWindows(activity.window, false)

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    val statusBarBg = safeAreaTopBackground ?: Color.Transparent
    SideEffect {
        //TODO set navigationBar color
//        systemUiController.setNavigationBarColor(backgroundColor, darkIcons = useDarkIcons)
        systemUiController.setStatusBarColor(statusBarBg, darkIcons = useDarkIcons)
    }
}
