package br.com.zup.nimbus.compose.layout.style.model

internal data class Margin(
    val margin: Double?,
    val marginStart: Double?,
    val marginEnd: Double?,
    val marginTop: Double?,
    val marginBottom: Double?,
    val marginHorizontal: Double?,
    val marginVertical: Double?,
) {
    companion object {
        val empty = Margin(null, null, null, null, null, null, null)
    }
}
