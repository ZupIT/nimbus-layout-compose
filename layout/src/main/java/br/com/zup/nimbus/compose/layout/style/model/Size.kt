package br.com.zup.nimbus.compose.layout.style.model

import br.com.zup.nimbus.compose.layout.utils.Either

enum class AdaptiveSize(val mode: String) {
    EXPAND("expand"),
    FIT_CONTENT("fitContent");

    companion object {
        fun fromName(mode: String): AdaptiveSize {
            return values().firstOrNull { it.mode == mode } ?: FIT_CONTENT
        }
    }
}

open class Size (
    val width: Either<AdaptiveSize, Double>? = Either.Left(AdaptiveSize.FIT_CONTENT),
    val height: Either<AdaptiveSize, Double>? = Either.Left(AdaptiveSize.FIT_CONTENT),
    val minWidth: Double?,
    val minHeight: Double?,
    val maxWidth: Double?,
    val maxHeight: Double?,
    val clipped: Boolean?,
)
