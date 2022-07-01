package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal interface Container: Box {
    val stretch: Boolean?
    val flex: Int?
    val crossAxisAlignment: CrossAxisAlignment?
    val mainAxisAlignment: MainAxisAlignment?
}

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ParentContainer(
    val crossAxisAlignment: CrossAxisAlignment? = CrossAxisAlignment.START
)
