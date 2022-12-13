package br.com.zup.nimbus.store.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.store.model.TextInputType

@Composable
@AutoDeserialize
fun TextInput(
    label: String,
    value: String?,
    type: TextInputType? = null,
    onChange: ((value: String) -> Unit)? = null,
    onBlur: ((value: String) -> Unit)? = null,
    onFocus: ((value: String) -> Unit)? = null,
) {
    val (hasFocus, setFocus) = remember {
        mutableStateOf(false)
    }

    val modifier = Modifier.onFocusChanged {
        if (it.isFocused) {
            onFocus?.let { it(value ?: "") }
            setFocus(true)
        }
        else {
            if (hasFocus) onBlur?.let { it(value ?: "") }
            setFocus(false)
        }
    }

    TextField(
        value = value ?: "",
        keyboardOptions = KeyboardOptions(keyboardType = (type ?: TextInputType.Text).keyboard),
        onValueChange = { newValue -> onChange?.let { it(newValue) } },
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
    )
}
