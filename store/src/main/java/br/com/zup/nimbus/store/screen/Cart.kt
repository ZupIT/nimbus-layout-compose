package br.com.zup.nimbus.store.screen

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.core.network.ViewRequest
import br.com.zup.nimbus.compose.NimbusNavigator

@Composable
fun CartScreen(key: String = "cart") {
    NimbusNavigator(ViewRequest("/cart"), viewModelKey = key)
}
