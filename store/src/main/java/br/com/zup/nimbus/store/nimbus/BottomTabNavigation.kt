package br.com.zup.nimbus.store.nimbus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.zup.nimbus.store.navigation.navigate
import com.zup.nimbus.core.ActionHandler

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

    val actionHandler: ActionHandler = {
        val route = it.action.properties?.get("route")
        val navController = nimbus.get(NAV_CONTROLLER_KEY)
        if (route is String && navController is NavController) navigate(navController, route)
    }
}