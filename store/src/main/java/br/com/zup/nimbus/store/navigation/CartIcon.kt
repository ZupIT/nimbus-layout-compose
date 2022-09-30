package br.com.zup.nimbus.store.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.zup.com.nimbus.compose.NimbusTheme

@Composable
fun CartIcon() {
    val nimbus = NimbusTheme.nimbus
    val coroutineScope = rememberCoroutineScope()
    val cart = remember { ObservableCart(nimbus, coroutineScope) }
    val numberOfItemsInCart = cart.collectAsState()

    DisposableEffect(Unit) {
        onDispose { cart.dispose() }
    }

    Box(contentAlignment = Alignment.TopEnd) {
        Icon(
            Icons.Rounded.ShoppingCart,
            contentDescription = "cart",
            modifier = Modifier.padding(end = 5.dp, top = 2.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(16.dp)
                .width(16.dp)
                .background(color = Color.Red, shape = CircleShape)
        ) {
            Text(
                text = "${numberOfItemsInCart.value}",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 1.dp)
            )
        }
    }
}
