package br.com.zup.nimbus.compose.layout.model

internal interface Box : Margin, Padding, Size, Border {
    val backgroundColor: String?
    val shadow: List<Shadow>?
    val children: Component?
}
