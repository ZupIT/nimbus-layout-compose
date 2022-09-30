package br.com.zup.nimbus.store.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable

sealed class BottomNavItem(
    val title: String,
    val icon: @Composable () -> Unit,
    val screenRoute: String,
) {
    object Products: BottomNavItem(
        "Products",
        { Icon(Icons.Rounded.List, contentDescription = "products") },
        "products",
    )
    object Cart: BottomNavItem(
        "Cart",
        { CartIcon() },
        "cart",
    )
    object Orders: BottomNavItem(
        "My Orders",
        { Icon(Icons.Rounded.AccountBox, contentDescription = "orders") },
        "orders",
    )
    object Exit: BottomNavItem(
        "Exit",
        { Icon(Icons.Rounded.ExitToApp, contentDescription = "exit") },
        "exit"
    )
}
