package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun Touchable(
    onPress: (Any?) -> Unit,
    children: @Composable () -> Unit,
    accessibility: Accessibility? = null,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable {
                onPress(null)
            }
            .accessibility(accessibility)
    ) {
        children()
    }
}
