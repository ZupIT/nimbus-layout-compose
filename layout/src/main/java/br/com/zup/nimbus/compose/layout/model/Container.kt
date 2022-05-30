package br.com.zup.nimbus.compose.layout.model

internal interface Container : Box {
    val flex: Int?
    //'stretch' | 'start' | 'end' | 'center', // default: start
    val crossAxisAlignment: String?
    //'start' | 'end' | 'center' | 'spaceBetween' | 'spaceAround' | 'spaceEvenly', // default: start
    val mainAxisAlignment: String?
}
