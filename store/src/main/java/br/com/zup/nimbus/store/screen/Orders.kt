package br.com.zup.nimbus.store.screen

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.NimbusNavigator
import br.com.zup.nimbus.core.network.ViewRequest

@Composable
fun OrdersScreen(key: String = "orders") {
    NimbusNavigator(ViewRequest("/orders"), viewModelKey = key)
}
