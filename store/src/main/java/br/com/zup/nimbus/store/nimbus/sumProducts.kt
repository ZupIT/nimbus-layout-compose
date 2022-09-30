package br.com.zup.nimbus.store.nimbus

import com.zup.nimbus.core.OperationHandler

val sumProducts: OperationHandler = {
    val products = it[0]
    var sum = 0.0
    if (products is List<*>) {
        products.forEach { product ->
            if (product is Map<*, *>) {
                val price = product["price"]
                sum += if (price is Number) price.toDouble() else 0.0
            }
        }
    }
    sum
}
