package br.com.zup.nimbus.compose.layout.sample.components

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.zup.nimbus.core.tree.ServerDrivenNode

internal fun <T> parse(obj: Any,typeRef: TypeReference<T>): T {
    val mapper = jacksonObjectMapper()
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    return mapper.convertValue(obj, typeRef)
}

internal fun <T> ServerDrivenNode.parse(typeRef: TypeReference<T>): T {
    return parse(this, typeRef)
}

internal fun <T> Map<String, Any?>.parse(typeRef: TypeReference<T>): T {
    return parse(this, typeRef)
}
