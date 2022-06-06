package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.sample.model.NimbusTextModel

@Composable
fun NimbusText(props: NimbusTextModel) {
    Text(text = props.text ?: "", maxLines = props.maxLines ?: Int.MAX_VALUE)
}
