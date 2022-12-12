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

package br.com.zup.nimbus.compose.layout.accessibility

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import br.com.zup.nimbus.compose.layout.extensions.isTrue

internal fun Modifier.accessibility(
    accessibility: Accessibility?,
    modifier: Modifier = Modifier,
) = this.then(
    accessibility?.label?.let { description ->
        modifier.semantics(mergeDescendants = true) {
            contentDescription = description
            if (accessibility.isHeader.isTrue()) {
                heading()
            }
        }
    } ?: modifier
)
