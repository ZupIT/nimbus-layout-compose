package br.com.zup.nimbus.compose.layout.extensions

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.model.SafeAreaEdges

private val AllWindowInsetsSides =
    WindowInsetsSides.Left +
            (WindowInsetsSides.Top) +
            (WindowInsetsSides.Right) +
            (WindowInsetsSides.Bottom)

private val AllSafeAreaEdges = mutableListOf(
    SafeAreaEdges.TOP,
    SafeAreaEdges.LEADING,
    SafeAreaEdges.TRAILING,
    SafeAreaEdges.BOTTOM)

/**
 * Transform the list of of SafeAreaEdges that must be ignored into WindowInsetsSides
 * that will have all WindowInsetsSides except the ones that must be ignored.
 * Returns null if you use all edge values.
 */
internal fun List<SafeAreaEdges>.toWindowInsetsSidesOnly(): WindowInsetsSides? {
    var sides: WindowInsetsSides? = null
    when {
        this.containsAll(AllSafeAreaEdges) -> {
            //Should not apply any SafeArea edges
            return null
        }
        this.isEmpty() -> {
            //Should apply the padding for all SafeArea edges
            return AllWindowInsetsSides
        }
        else -> {

            val allEdges = mutableListOf<SafeAreaEdges>()
            allEdges.addAll(AllSafeAreaEdges)

            this.forEach {
                allEdges.remove(it)
            }

            allEdges.forEach { currentEdge ->
                sides = sides?.let { it + currentEdge.toWindowInsetsSide() }
                    ?: currentEdge.toWindowInsetsSide()
            }
        }
    }
    return sides
}

@Composable
internal fun WindowInsets.ignoreSafeArea(edgesToIgnore: List<SafeAreaEdges>): WindowInsets =
    this.let { windowInsets: WindowInsets ->
        var insets: WindowInsets = windowInsets
        //Null here means no sides to apply and should ignore all edges
        //and write on the safearea.
        val windowInsetsSides = edgesToIgnore.toWindowInsetsSidesOnly()

        insets = if (windowInsetsSides != null) {
            this.only(windowInsetsSides)
        } else {
            insets.exclude(this)
        }
        return@let insets
    }
