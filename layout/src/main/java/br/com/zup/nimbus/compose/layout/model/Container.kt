package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal open class Container(
    val flex: Int? = null,
    //'stretch' | 'start' | 'end' | 'center', // default: start
    val crossAxisAlignment: String? = "start",
    //'start' | 'end' | 'center' | 'spaceBetween' | 'spaceAround' | 'spaceEvenly', // default: start
    val mainAxisAlignment: String? = "start",
    override val backgroundColor: String? = null,
    override val shadow: List<Shadow>? = null,
    override var children: Component? = null,
    override val width: Double? = null,
    override val height: Double? = null,
    override val minWidth: Double? = null,
    override val minHeight: Double? = null,
    override val maxWidth: Double? = null,
    override val maxHeight: Double? = null,
    override val clipped: Boolean? = false,
    override val borderWidth: Double? = 0.0,
    override val borderDashLength: Double? = 1.0,
    override val borderDashSpacing: Double? = 0.0,
    override val cornerRadius: Double? = 0.0,
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
) : Box
