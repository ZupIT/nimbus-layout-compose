package br.com.zup.nimbus.compose.layout.model

internal interface WithAccessibility {
    val accessibility: Accessibility?
}

internal class Accessibility(
    val label: String? = null,
    val isHeader: Boolean? = null,
)
