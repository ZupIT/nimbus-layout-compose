package br.com.zup.nimbus.compose.layout.style.model

import kotlin.math.roundToInt

sealed class AdaptiveSize {
    object Expand : AdaptiveSize()
    object FitContent : AdaptiveSize()
    class Fixed(val value: Double): AdaptiveSize()

    override fun equals(other: Any?): Boolean {
        return this === other || (this is Fixed && other is Fixed && this.value == other.value)
    }

    override fun hashCode(): Int {
        return when(this) {
            is Fixed -> (this.value * 100000).roundToInt()
            Expand -> -1
            FitContent -> -2
        }
    }
}

enum class DirectionScope { Row, Column, Stack }

open class Size (
    val width: AdaptiveSize?,
    val height: AdaptiveSize?,
    val directionScope: DirectionScope,
    val minWidth: Double?,
    val minHeight: Double?,
    val maxWidth: Double?,
    val maxHeight: Double?,
    val clipped: Boolean?,
) {
    companion object {
        val empty = Size(null, null, DirectionScope.Column, null, null, null, null, null)
    }
}
