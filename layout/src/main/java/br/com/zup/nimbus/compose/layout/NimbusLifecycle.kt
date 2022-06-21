package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import br.com.zup.nimbus.compose.layout.model.Component

@Composable
internal fun NimbusLifecycle(
    modifier: Modifier = Modifier,
    onInit: ((Any?) -> Unit)? = null,
    content: Component,
) {
    ObserveLifecycle(onInit = onInit)

    Column(modifier = modifier) {
        content()
    }
}

@Composable
private fun ObserveLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onInit: ((Any?) -> Unit)? = null
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                onInit?.invoke(null)
            }
        }
        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

