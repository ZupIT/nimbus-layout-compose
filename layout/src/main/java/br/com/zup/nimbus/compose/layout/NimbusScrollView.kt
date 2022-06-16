package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.scroll
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ScrollViewApi

@Composable
internal fun NimbusScrollView(
    modifier: Modifier = Modifier,
    model: ScrollViewApi,
    verticalScrollState: ScrollState = rememberScrollState(),
    horizontalScrollState: ScrollState = rememberScrollState(),
    content: Component,
) {
    val scrollView = requireNotNull(model.properties)
    Box(
        modifier = modifier
            .scroll(scrollView = scrollView,
                verticalScrollState = verticalScrollState,
                horizontalScrollState = horizontalScrollState)
    ) {
        content()
    }
}

