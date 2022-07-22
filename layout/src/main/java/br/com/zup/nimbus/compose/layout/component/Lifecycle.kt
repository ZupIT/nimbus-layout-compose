package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.zup.nimbus.processor.ServerDrivenComponent

@Composable
@ServerDrivenComponent
internal fun Lifecycle(
    onInit: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    ObserveLifecycle(onInit = onInit)

    Column {
        content()
    }
}

@Composable
private fun ObserveLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onInit: (() -> Unit)? = null
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                onInit?.invoke()
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

