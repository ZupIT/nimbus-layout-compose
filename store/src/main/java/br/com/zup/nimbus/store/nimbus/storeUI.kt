package br.com.zup.nimbus.store.nimbus

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import br.zup.com.nimbus.compose.ui.NimbusComposeUILibrary
import br.com.zup.nimbus.store.component.StoreButton
import br.com.zup.nimbus.store.component.TextInput

val storeUI = NimbusComposeUILibrary("store")
    .addComponent("button") @Composable { StoreButton(it) }
    .addComponent("textInput") @Composable { TextInput(it) }
    .addComponent("spinner") @Composable { CircularProgressIndicator() }
    .addOperation("formatPrice", formatPrice)
    .addOperation("sumProducts", sumProducts)
    .addAction("changeBottomNavigatorRoute", BottomTabNavigation.actionHandler)
