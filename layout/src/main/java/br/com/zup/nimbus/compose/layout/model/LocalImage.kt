package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class LocalImageApi: AbstractComponentApi<LocalImage>(LocalImage())

@JsonIgnoreProperties(ignoreUnknown = true)
internal class LocalImage(
    override val scale: ImageScale? = ImageScale.CENTER,
    val id: String? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val accessibility: Accessibility? = null,
) : BaseImage
