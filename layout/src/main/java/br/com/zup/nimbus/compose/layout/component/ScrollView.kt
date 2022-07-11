package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.scroll
import br.com.zup.nimbus.compose.layout.model.Component
import br.com.zup.nimbus.compose.layout.model.ScrollViewModel

@Composable
internal fun ScrollView(
    model: ScrollViewModel,
    modifier: Modifier = Modifier,
    verticalScrollState: ScrollState = rememberScrollState(),
    horizontalScrollState: ScrollState = rememberScrollState(),
    content: Component,
) {
    Column(
        modifier = modifier
            .scroll(
                scrollView = model,
                verticalScrollState = verticalScrollState,
                horizontalScrollState = horizontalScrollState
            )
    ) {
        content()
    }
}

