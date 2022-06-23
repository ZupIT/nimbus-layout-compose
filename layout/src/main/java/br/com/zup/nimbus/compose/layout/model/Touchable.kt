package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class TouchableApi: AbstractComponentApi<TouchableModel>(TouchableModel())

@JsonIgnoreProperties(ignoreUnknown = true)
internal class TouchableModel(
    override val accessibility: Accessibility? = null,
) : WithAccessibility
