package br.com.zup.nimbus.compose.layout.style.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.layout.extensions.color

internal fun Modifier.background(color: String?): Modifier {
    return color?.let { this.background(it.color) } ?: this
}