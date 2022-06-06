package br.com.zup.nimbus.compose.layout.model

internal interface Border {
    val borderWidth: Double? // default: 0
    val borderDashLength: Double? // default: 1
    val borderDashSpacing: Double? // default: 0
    val cornerRadius: Double? // default: 0
    val borderColor: String? // default: black

    // The next four items are extras
    /*topLeftRadius?: double,
    topRightRadius?: double,
    bottomLeftRadius?: double,
    bottomRightRadius?: double*/
}
