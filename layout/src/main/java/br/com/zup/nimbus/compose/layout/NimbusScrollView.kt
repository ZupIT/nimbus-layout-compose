package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.scroll
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ScrollViewApi

@Composable
internal fun NimbusScrollView(
    model: ScrollViewApi,
    modifier: Modifier = Modifier,
    verticalScrollState: ScrollState = rememberScrollState(),
    horizontalScrollState: ScrollState = rememberScrollState(),
    content: Component,
) {
    val scrollView = requireNotNull(model.properties)
    Column(
        modifier = modifier
            .scroll(scrollView = scrollView,
                verticalScrollState = verticalScrollState,
                horizontalScrollState = horizontalScrollState)
    ) {
        content()
    }
}

