package br.com.zup.nimbus.compose.layout.component

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Disables hardware acceleration for the content if the condition matches
 */
@Composable
fun NimbusSoftwareLayer(
    modifier: Modifier = Modifier,
    condition: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (condition) {
        AndroidView(
            factory = { context ->
                ComposeView(context).apply {
                    setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                }
            },
            update = { composeView ->
                composeView.setContent(content)
            },
            modifier = modifier,
        )
    } else {
        content()
    }
}
