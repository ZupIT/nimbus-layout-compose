package br.com.zup.nimbus.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.zup.nimbus.store.screen.CartScreen
import br.com.zup.nimbus.store.screen.ExitScreen
import br.com.zup.nimbus.store.screen.OrdersScreen
import br.com.zup.nimbus.store.screen.ProductsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Products.screenRoute) {
        composable(BottomNavItem.Products.screenRoute) {
            ProductsScreen("products${navController}")
        }
        composable(BottomNavItem.Cart.screenRoute) {
            CartScreen("cart${navController}")
        }
        composable(BottomNavItem.Orders.screenRoute) {
            OrdersScreen()
        }
        composable(BottomNavItem.Exit.screenRoute) {
            ExitScreen()
        }
    }
}

fun navigate(navController: NavController, destination: String) {
    navController.navigate(destination) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
