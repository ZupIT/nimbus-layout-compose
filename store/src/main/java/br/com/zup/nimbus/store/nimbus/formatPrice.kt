package br.com.zup.nimbus.store.nimbus

import com.zup.nimbus.processor.annotation.AutoDeserialize
import java.text.NumberFormat
import java.util.Currency

@AutoDeserialize
fun formatPrice(value: Double, currency: String): String {
    val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
    formatter.maximumFractionDigits = 2
    formatter.currency = Currency.getInstance(currency)
    return formatter.format(value)
}
