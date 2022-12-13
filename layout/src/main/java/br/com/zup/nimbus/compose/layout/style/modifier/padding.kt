package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.style.model.Padding

internal fun Modifier.paddingStyle(style: Padding): Modifier {
    return this.padding(style.toPaddingValues())
}
