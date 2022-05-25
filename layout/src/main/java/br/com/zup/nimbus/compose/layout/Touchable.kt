package br.com.zup.nimbus.compose.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics

@Composable
internal fun Touchable(
    onPress: (Any?) -> Unit,
    children: @Composable () -> Unit,
    accessibility: Accessibility? = null,
    modifier: Modifier = Modifier,
) {
    var newModifier = modifier
        .clickable {
            onPress(null)
        }

    accessibility?.let { accessibility ->
        newModifier = newModifier.semantics {
            if (accessibility.isHeader == true) {
                heading()
            }
            accessibility.label?.let {
                contentDescription = it
            }
        }
    }

    Box(
        modifier = newModifier
    ) {
        children()
    }
}
