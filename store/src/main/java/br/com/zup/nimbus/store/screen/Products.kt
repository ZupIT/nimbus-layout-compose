package br.com.zup.nimbus.store.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.compose.NimbusNavigator
import br.com.zup.nimbus.core.network.ViewRequest

@Composable
fun ProductsScreen(key: String = "products") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NimbusNavigator(ViewRequest("/products"), viewModelKey = key)
    }
}
