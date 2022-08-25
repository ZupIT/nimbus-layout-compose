package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.runtime.Composable
import br.zup.com.nimbus.compose.ComponentLibrary

val materialComponents = ComponentLibrary("material")
    .add("button") @Composable { Button(it) }
    .add("text") @Composable { Text(it) }

