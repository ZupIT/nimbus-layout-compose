package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zup.nimbus.processor.ServerDrivenComponent

@Composable
@ServerDrivenComponent
fun Text(
    text: String,
    maxLines: Int?
) {
    Text(text = text, maxLines = maxLines ?: Int.MAX_VALUE)
}
