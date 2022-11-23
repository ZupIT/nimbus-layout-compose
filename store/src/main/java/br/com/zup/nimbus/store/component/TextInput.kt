package br.com.zup.nimbus.store.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.store.model.TextInputType

@Composable
@AutoDeserialize
fun TextInput(
    label: String,
    value: String,
    type: TextInputType? = null,
    enabled: Boolean? = null,
    onChange: ((value: String) -> Unit)? = null,
    onBlur: ((value: String) -> Unit)? = null,
    onFocus: ((value: String) -> Unit)? = null,
) {

    val modifier = Modifier.onFocusChanged {
        if (it.isFocused) onFocus?.let { it(value) }
        else onBlur?.let { it(value) }
    }

    TextField(
        value = value,
        enabled = enabled == true,
        keyboardOptions = KeyboardOptions(keyboardType = (type ?: TextInputType.Text).keyboard),
        onValueChange = { newValue -> onChange?.let { it(newValue) } },
        label = { Text(label) },
        modifier = modifier,
    )
}
