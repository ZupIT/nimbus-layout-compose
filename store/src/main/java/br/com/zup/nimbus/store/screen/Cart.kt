package br.com.zup.nimbus.store.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.zup.com.nimbus.compose.NimbusNavigator
import com.zup.nimbus.core.network.ViewRequest

@Composable
fun CartScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        NimbusNavigator(ViewRequest("/cart"))
        //NimbusNavigator(json = SCROLL_VIEW_TEST)
    }
}