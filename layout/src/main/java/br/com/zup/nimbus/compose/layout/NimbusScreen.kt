package br.com.zup.nimbus.compose.layout

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
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
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ScreenApi
import br.com.zup.nimbus.compose.layout.model.toWindowInsetsSidesOnly
import br.zup.com.nimbus.compose.NimbusTheme
import br.zup.com.nimbus.compose.core.ui.internal.NimbusNavHostHelper
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun NimbusScreen(
    modifier: Modifier = Modifier,
    model: ScreenApi,
    navHostHelper: NimbusNavHostHelper = NimbusTheme.nimbusNavigatorState.navHostHelper,
    content: Component,
) {
    val screen = requireNotNull(model.properties)
    val ignoreSafeArea = requireNotNull(screen.ignoreSafeArea)

    ConfigureSafeArea(ignoreSafeArea.isNotEmpty())

    Scaffold(
        topBar = {
            // We use TopAppBar from accompanist-insets-ui which allows us to provide
            // content padding matching the system bars insets.
            val topAppBarPadding =
                    WindowInsets.statusBars
                        .only(ignoreSafeArea.toWindowInsetsSidesOnly())
                        .asPaddingValues()

            TopAppBar(
                title = { Text(screen.title ?: "") },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                navigationIcon = ifNavigationIcon(
                    showBackButton = !navHostHelper.isFirstScreen() &&
                            screen.showBackButton.isTrue()
                ) { navHostHelper.pop() },
                contentPadding = topAppBarPadding,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { contentPadding ->
        Box(modifier.padding(contentPadding)) {
            content()
        }
    }
}

@Composable
private fun ifNavigationIcon(
    showBackButton: Boolean? = true,
    onClick: () -> Unit = {},
): @Composable (() -> Unit)? {
    return if (showBackButton == true) {
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
internal fun ConfigureSafeArea(shouldIgnoreSafeArea: Boolean = false) {
    // Turn off the decor fitting system windows, which means we need to through handling
    // insets

    if (shouldIgnoreSafeArea) {
        val activity = LocalContext.current as Activity
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
    }

    // Update the system bars to be translucent
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
    }
}

