package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class TouchableModel(
    val onPress: Action,
    override val accessibility: Accessibility? = null,
) : WithAccessibility
