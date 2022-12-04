package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.ui.NimbusComposeUILibrary

val materialComponents = NimbusComposeUILibrary("material")
    .addComponent("button") @Composable { Button(it) }
    .addComponent("text") @Composable { Text(it) }

