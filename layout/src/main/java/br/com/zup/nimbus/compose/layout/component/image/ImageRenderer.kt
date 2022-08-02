package br.com.zup.nimbus.compose.layout.component.image

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.accessibility.accessibility
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.style.modifier.sizeStyle
import br.com.zup.nimbus.compose.layout.viewmodel.ImageViewModelState

private const val LOG_TAG = "NimbusImage"

@Composable
internal fun ImageRenderer(
    viewModelState: ImageViewModelState,
    style: Size?,
    contentScale: ContentScale?,
    accessibility: Accessibility?,
) {
    var modifier = if(style == null) Modifier else Modifier.sizeStyle(style)
    when (viewModelState) {
        is ImageViewModelState.ImageResource -> {
            var description: String? = null
            if (!viewModelState.isPlaceholder) {
                description = accessibility?.label
                modifier = modifier.accessibility(accessibility)
            }
            androidx.compose.foundation.Image(
                painter = painterResource(viewModelState.id),
                contentDescription = description,
                modifier = modifier,
                contentScale = contentScale ?: ContentScale.Inside
            )
        }
        is ImageViewModelState.Error -> {
            val throwable = viewModelState.throwable
            Log.d(LOG_TAG, throwable.message ?: "", throwable)
            Box(modifier = modifier)
        }
        is ImageViewModelState.BitmapImage -> {
            modifier = modifier.accessibility(accessibility)
            androidx.compose.foundation.Image(
                bitmap = viewModelState.bitmap.asImageBitmap(),
                contentDescription = accessibility?.label,
                modifier = modifier,
                contentScale = contentScale ?: ContentScale.Inside
            )
        }
        is ImageViewModelState.Nothing -> {}
    }
}
