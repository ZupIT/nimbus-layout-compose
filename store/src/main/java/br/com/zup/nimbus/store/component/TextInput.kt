package br.com.zup.nimbus.store.component

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import com.zup.nimbus.processor.ServerDrivenComponent

typealias InputCallback = (value: String) -> Unit

@Composable
@ServerDrivenComponent
fun TextInput(
    label: String,
    value: String?,
    onChange: InputCallback? = null,
    onBlur: InputCallback? = null,
    onFocus: InputCallback? = null,
) {
    TextField(
        value = value ?: "",
        onValueChange = {
            if (onChange != null) onChange(it)
        },
        label = { Text(label) },
        modifier = Modifier.onFocusChanged {
            if (it.isFocused && onFocus != null) onFocus(value ?: "")
            else if (onBlur != null) onBlur(value ?: "")
        }
    )
}