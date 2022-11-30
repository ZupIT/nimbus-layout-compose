package br.com.zup.nimbus.store.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.zup.nimbus.core.network.ViewRequest
import br.zup.com.nimbus.compose.NimbusNavigator

@Composable
fun CartScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        NimbusNavigator(ViewRequest("/cart"))
        //NimbusNavigator(json = SCROLL_VIEW_TEST)
    }
}
