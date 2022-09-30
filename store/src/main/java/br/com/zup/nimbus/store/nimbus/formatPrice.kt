package br.com.zup.nimbus.store.nimbus

import com.zup.nimbus.core.OperationHandler
import java.text.NumberFormat
import java.util.Currency

val formatPrice: OperationHandler = {
    val value = (it[0] as Number).toDouble()
    val currency = it[1] as String

    val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
    formatter.maximumFractionDigits = 2
    formatter.currency = Currency.getInstance(currency)

    formatter.format(value)
}
