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
        "Orders",
        { Icon(Icons.Rounded.AccountBox, contentDescription = "orders") },
        "orders",
    )
    object Exit: BottomNavItem(
        "Exit",
        { Icon(Icons.Rounded.ExitToApp, contentDescription = "exit") },
        "exit"
    )
}
