package br.com.zup.nimbus.store.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.NimbusNavigator
import br.com.zup.nimbus.core.network.ViewRequest

@Composable
fun ProductsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        NimbusNavigator(ViewRequest("/products"))
    }
}
