package br.com.zup.nimbus.store.nimbus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.zup.nimbus.annotation.AutoDeserialize
import br.com.zup.nimbus.store.navigation.navigate

private const val NAV_CONTROLLER_KEY = "navController"

object BottomTabNavigation {
    @Composable
    fun SideEffect() {
        val navController = rememberNavController()

        DisposableEffect(navController) {
            nimbus.set(NAV_CONTROLLER_KEY, navController)

            onDispose {
                nimbus.unset(NAV_CONTROLLER_KEY)
            }
        }
    }

    @AutoDeserialize
    fun changeTab(route: String) {
        val navController = nimbus.get(NAV_CONTROLLER_KEY)
        if (navController is NavController) navigate(navController, route)
    }
}
