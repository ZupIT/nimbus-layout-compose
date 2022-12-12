/*
 * Copyright 2023 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.nimbus.compose.layout.component.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.only
import androidx.compose.runtime.Composable

private val AllWindowInsetsSides =
    WindowInsetsSides.Left +
    (WindowInsetsSides.Top) +
    (WindowInsetsSides.Right) +
    (WindowInsetsSides.Bottom)

private val AllSafeAreaEdges = SafeAreaEdges.values().toMutableList()

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

            for (edge in this) {
                allEdges.remove(edge)
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
