/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
            OrdersScreen("orders${navController}")
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
