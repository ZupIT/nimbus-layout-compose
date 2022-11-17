package br.com.zup.nimbus.compose.layout.component.scroll

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.annotation.Alias
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.annotation.Ignore

@Composable
@AutoDeserialize
internal fun ScrollView(
    direction: ScrollViewDirection?,
    scrollIndicator: Boolean?,
    @Ignore verticalScrollState: ScrollState = rememberScrollState(),
    @Ignore horizontalScrollState: ScrollState = rememberScrollState(),
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.scroll(
            direction = direction ?: ScrollViewDirection.Vertical,
            scrollIndicator = scrollIndicator ?: true,
            verticalScrollState = verticalScrollState,
            horizontalScrollState = horizontalScrollState
        )
    ) {
        content()
    }
}

