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

package br.com.zup.nimbus.compose.layout

const val LIBRARY_NAME = "layout"

internal object ComponentNames {
    const val ROW = "row"
    const val FLOW_ROW = "flowRow"
    const val COLUMN = "column"
    const val FLOW_COLUMN = "flowColumn"
    const val TOUCHABLE = "touchable"
    const val LAZY_ROW = "lazyRow"
    const val LAZY_COLUMN = "lazyColumn"
    const val POSITIONED = "positioned"
    const val STACK = "stack"
    const val REMOTE_IMAGE = "remoteImage"
    const val LOCAL_IMAGE = "localImage"
    const val SCROLL_VIEW = "scrollView"
    const val SCREEN = "screen"
    const val TEXT = "text"
    const val LIFECYCLE = "lifecycle"
}

fun getFullNameOfComponent(name: String): String {
    return "$LIBRARY_NAME:$name"
}
