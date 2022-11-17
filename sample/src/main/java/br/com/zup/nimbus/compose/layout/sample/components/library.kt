package br.com.zup.nimbus.compose.layout.sample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import br.zup.com.nimbus.compose.ui.NimbusComposeUILibrary
import com.zup.nimbus.core.deserialization.AnyServerDrivenData

val materialComponents = NimbusComposeUILibrary("material")
    .addComponent("button") @Composable { Button(it) }
    .addComponent("text") @Composable { Text(it) }

