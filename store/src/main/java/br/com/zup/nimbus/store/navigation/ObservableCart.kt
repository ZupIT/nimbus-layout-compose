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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import br.com.zup.nimbus.compose.Nimbus
import br.com.zup.nimbus.core.ServerDrivenState
import br.com.zup.nimbus.core.dependency.Dependent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ObservableCart(nimbus: Nimbus, private val scope: CoroutineScope): Dependent {
    private val flow = MutableStateFlow(0)
    private val global: ServerDrivenState?

    init {
        global = nimbus.states?.last()
        global?.addDependent(this)
    }

    @Composable
    fun collectAsState(): State<Int> = flow.collectAsState()

    fun dispose() {
        global?.removeDependent(this)
    }

    override fun update() {
        val global = global?.get()
        if (global is Map<*, *>) {
            val cart = global["cart"]
            if (cart is List<*>) {
                scope.launch {
                    flow.emit(cart.size)
                }
            }
        }
    }
}
