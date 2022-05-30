package br.com.zup.nimbus.compose.layout.model

internal interface Size {
    val width: Double?
    val height: Double?
    val minWidth: Double?
    val minHeight: Double?
    val maxWidth: Double?
    val maxHeight: Double?
    val clipped: Boolean? // default: false
}
