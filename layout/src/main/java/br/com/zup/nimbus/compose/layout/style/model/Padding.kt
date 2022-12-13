package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

internal data class Padding(
    val padding: Double?,
    val paddingStart: Double?,
    val paddingEnd: Double?,
    val paddingTop: Double?,
    val paddingBottom: Double?,
    val paddingHorizontal: Double?,
    val paddingVertical: Double?,
) {
    companion object {
        val empty = Padding(null, null, null, null, null, null, null)
    }
    
    fun toPaddingValues(): PaddingValues {
        var paddingStart: Double? = null
        var paddingEnd: Double? = null
        var paddingTop: Double? = null
        var paddingBottom: Double? = null

        padding?.let {
            paddingStart = it
            paddingEnd = it
            paddingTop = it
            paddingBottom = it
        }

        paddingHorizontal?.let {
            paddingStart = it
            paddingEnd = it
        }

        paddingVertical?.let {
            paddingTop = it
            paddingBottom = it
        }

        paddingStart?.let {
            paddingStart = it
        }

        paddingEnd?.let {
            paddingEnd = it
        }

        paddingTop?.let {
            paddingTop = it
        }

        paddingBottom?.let {
            paddingBottom = it
        }

        return PaddingValues(
            (paddingStart ?: 0.0).dp,
            (paddingTop ?: 0.0).dp,
            (paddingEnd ?: 0.0).dp,
            (paddingBottom ?: 0.0).dp,
        )
    }
}
