package br.com.zup.nimbus.compose.layout.style.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import br.com.zup.nimbus.compose.layout.style.model.AdaptiveSize
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.utils.Either

@SuppressLint("ModifierFactoryExtensionFunction")
private fun handleAdaptiveWidth(
    modifier: Modifier,
    width: AdaptiveSize,
    minWidth: Double?,
    maxWidth: Double?
): Modifier {
    return when (width) {
        AdaptiveSize.EXPAND -> {
            if (maxWidth != null) {
                modifier.widthIn(max = maxWidth.dp)
            } else {
                modifier.fillMaxWidth()
            }
        }
        AdaptiveSize.FIT_CONTENT -> {
            var changedModifier = modifier
            minWidth?.let { changedModifier = changedModifier.widthIn(min = it.dp) }
            maxWidth?.let { changedModifier = changedModifier.widthIn(max = it.dp) }
            changedModifier
        }
    }
}

@SuppressLint("ModifierFactoryExtensionFunction")
private fun handleAdaptiveHeight(
    modifier: Modifier,
    height: AdaptiveSize,
    minHeight: Double?,
    maxHeight: Double?
): Modifier {
    return when (height) {
        AdaptiveSize.EXPAND -> {
            if (maxHeight != null) {
                modifier.heightIn(max = maxHeight.dp)
            } else {
                modifier.fillMaxHeight()
            }
        }
        AdaptiveSize.FIT_CONTENT -> {
            var changedModifier = modifier
            minHeight?.let { changedModifier = changedModifier.heightIn(min = it.dp) }
            maxHeight?.let { changedModifier = changedModifier.heightIn(max = it.dp) }
            changedModifier
        }
    }
}

internal fun Modifier.sizeStyle(style: Size): Modifier {
    var current = this
    val width = style.width ?: Either.Left(AdaptiveSize.FIT_CONTENT)
    val height = style.height ?: Either.Left(AdaptiveSize.FIT_CONTENT)

    current = when (width) {
        is Either.Right -> current.width(width.value.dp)
        is Either.Left -> handleAdaptiveWidth(current, width.value, style.minWidth, style.maxWidth)
    }

    current = when (height) {
        is Either.Right -> current.height(height.value.dp)
        is Either.Left -> handleAdaptiveHeight(current, height.value, style.minHeight, style.maxHeight)
    }

    if (style.clipped == true) current = current.clipToBounds()

    return current
}
