package br.com.zup.nimbus.store.component

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import br.com.zup.nimbus.annotation.AutoDeserialize

@Composable
@AutoDeserialize
fun StoreButton(text: String, enabled: Boolean?, onPress: (() -> Unit)?) {
    Button(onClick = onPress ?: {}, enabled = enabled != false) {
        Text(text)
    }
}
