package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class RemoteImageApi: AbstractComponentApi<RemoteImage>(RemoteImage())

@JsonIgnoreProperties(ignoreUnknown = true)
internal class RemoteImage(
    override val scale: ImageScale? = ImageScale.CENTER,
    val url: String? = null,
    val placeholder: String? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val accessibility: Accessibility? = null,
) : BaseImage
