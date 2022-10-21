package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zup.nimbus.processor.annotation.AutoDeserialize

@Composable
@AutoDeserialize
fun Text(
    text: String,
    maxLines: Int?
) {
    Text(text = text, maxLines = maxLines ?: Int.MAX_VALUE)
}
