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

package br.com.zup.nimbus.compose.layout.style.model

internal data class Margin(
    val margin: Double?,
    val marginStart: Double?,
    val marginEnd: Double?,
    val marginTop: Double?,
    val marginBottom: Double?,
    val marginHorizontal: Double?,
    val marginVertical: Double?,
) {
    companion object {
        val empty = Margin(null, null, null, null, null, null, null)
    }
}
