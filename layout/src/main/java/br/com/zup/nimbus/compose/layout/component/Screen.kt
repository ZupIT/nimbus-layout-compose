package br.com.zup.nimbus.compose.layout.component

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
import br.com.zup.nimbus.compose.layout.extensions.ignoreSafeArea
import br.com.zup.nimbus.compose.layout.extensions.isTrue
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ScreenModel
import br.zup.com.nimbus.compose.NimbusTheme
import br.zup.com.nimbus.compose.core.ui.internal.NimbusNavHostHelper
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun Screen(
    model: ScreenModel,
    modifier: Modifier = Modifier,
    navHostHelper: NimbusNavHostHelper = NimbusTheme.nimbusNavigatorState.navHostHelper,
    content: Component,
) {
    val ignoreSafeArea = requireNotNull(model.ignoreSafeArea)

    ConfigureSafeArea()

    Scaffold(
        modifier = modifier,
        topBar = {
            // We use TopAppBar from accompanist-insets-ui which allows us to provide
            // content padding matching the system bars insets.
            TopAppBar(
                title = { Text(model.title ?: "") },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                navigationIcon = shouldShowNavigationIcon(
                    showBackButton = !navHostHelper.isFirstScreen() &&
                            model.showBackButton.isTrue()
                ) { navHostHelper.pop() },
                contentPadding = WindowInsets.statusBars
                    .ignoreSafeArea(edgesToIgnore = ignoreSafeArea)
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

