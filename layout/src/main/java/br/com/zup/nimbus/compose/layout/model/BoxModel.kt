package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class BoxModel (
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val borderWidth: Double? = null,
    override val borderDashLength: Double? = null,
    override val borderDashSpacing: Double? = null,
    override val borderColor: String? = null,
    override val cornerRadius: Double? = null,
    override val margin: Double? = null,
    override val marginStart: Double? = null,
    override val marginEnd: Double? = null,
    override val marginTop: Double? = null,
    override val marginBottom: Double? = null,
    override val marginHorizontal: Double? = null,
    override val marginVertical: Double? = null,
    override val padding: Double? = null,
    override val paddingStart: Double? = null,
    override val paddingEnd: Double? = null,
    override val paddingTop: Double? = null,
    override val paddingBottom: Double? = null,
    override val paddingHorizontal: Double? = null,
    override val paddingVertical: Double? = null,
    override val backgroundColor: String? = null,
    override val shadow: List<Shadow>? = null,
    override val children: Component? = null
) : Box
