package br.com.zup.nimbus.compose.layout.style.model

import br.com.zup.nimbus.compose.layout.deserialization.AdaptiveSizeDeserializer
import br.com.zup.nimbus.compose.layout.deserialization.DirectionScopeDeserializer
import com.zup.nimbus.processor.Computed
import com.zup.nimbus.processor.Root
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
    @Root @Computed(AdaptiveSizeDeserializer::class) val width: AdaptiveSize? = null,
    @Root @Computed(AdaptiveSizeDeserializer::class) val height: AdaptiveSize? = null,
    @Root @Computed(DirectionScopeDeserializer::class) val directionScope: DirectionScope,
    val minWidth: Double?,
    val minHeight: Double?,
    val maxWidth: Double?,
    val maxHeight: Double?,
    val clipped: Boolean?,
)
