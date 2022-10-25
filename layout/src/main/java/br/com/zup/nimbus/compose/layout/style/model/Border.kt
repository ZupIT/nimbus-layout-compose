package br.com.zup.nimbus.compose.layout.style.model

internal data class Border(
    val borderWidth: Double?, // default: 0
    val borderDashLength: Double?, // default: 1
    val borderDashSpacing: Double?, // default: 0
    val cornerRadius: Double?, // default: 0
    val borderColor: String?, // default: black
)
