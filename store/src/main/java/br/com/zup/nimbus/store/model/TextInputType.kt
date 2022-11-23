package br.com.zup.nimbus.store.model

import androidx.compose.ui.text.input.KeyboardType

enum class TextInputType(val keyboard: KeyboardType) {
    Text(KeyboardType.Text),
    Password(KeyboardType.Password),
    Email(KeyboardType.Email),
    Number(KeyboardType.Number),
}
