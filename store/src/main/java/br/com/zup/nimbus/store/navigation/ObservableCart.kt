package br.com.zup.nimbus.store.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import br.com.zup.nimbus.compose.Nimbus
import br.com.zup.nimbus.core.ServerDrivenState
import br.com.zup.nimbus.core.dependency.Dependent
import br.com.zup.nimbus.core.deserialization.AnyServerDrivenData
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
        val products = AnyServerDrivenData(global?.get()).get("cart").get("products").asList()
        val count = products.sumOf { it.get("quantity").asInt()  }
        scope.launch {
            flow.emit(count)
        }
    }
}
