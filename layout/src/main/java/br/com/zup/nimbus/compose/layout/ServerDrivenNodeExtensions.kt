package br.com.zup.nimbus.compose.layout

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.zup.nimbus.core.tree.ServerDrivenNode

fun <T> ServerDrivenNode.parse(typeRef: TypeReference<T>): T {
    val mapper = jacksonObjectMapper()
    return mapper.convertValue(this.properties, typeRef)
}

fun <T> Map<String, Any?>?.parse(typeRef: TypeReference<T>): T {
    val mapper = jacksonObjectMapper()
    return mapper.convertValue(this, typeRef)
}
