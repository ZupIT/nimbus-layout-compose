/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
