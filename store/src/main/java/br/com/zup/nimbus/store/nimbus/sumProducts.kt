package br.com.zup.nimbus.store.nimbus

import br.com.zup.nimbus.store.model.Product
import br.com.zup.nimbus.annotation.AutoDeserialize

@AutoDeserialize
fun sumProducts(products: List<Product>): Double = products.sumOf { it.price }
