package br.com.zup.nimbus.compose.layout.sample.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NimbusTextModel (
    val text: String? = null,
    val maxLines: Int? = Int.MAX_VALUE
)
