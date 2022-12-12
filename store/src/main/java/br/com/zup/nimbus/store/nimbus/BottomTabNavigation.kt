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
