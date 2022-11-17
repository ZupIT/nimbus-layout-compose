package br.com.zup.nimbus.compose.layout.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.accessibility.Accessibility
import br.com.zup.nimbus.compose.layout.accessibility.accessibility
import br.com.zup.nimbus.annotation.AutoDeserialize

@Composable
@AutoDeserialize
internal fun Touchable(
    onPress: () -> Unit,
    accessibility: Accessibility? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = Modifier.clickable { onPress() }.accessibility(accessibility)) {
        content()
    }
}
