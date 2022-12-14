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

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.component.FlowColumn
import br.com.zup.nimbus.compose.layout.component.FlowRow
import br.com.zup.nimbus.compose.layout.component.Lifecycle
import br.com.zup.nimbus.compose.layout.component.Positioned
import br.com.zup.nimbus.compose.layout.component.Stack
import br.com.zup.nimbus.compose.layout.component.Touchable
import br.com.zup.nimbus.compose.layout.component.container.Column
import br.com.zup.nimbus.compose.layout.component.container.LazyColumn
import br.com.zup.nimbus.compose.layout.component.container.LazyRow
import br.com.zup.nimbus.compose.layout.component.container.Row
import br.com.zup.nimbus.compose.layout.component.image.LocalImage
import br.com.zup.nimbus.compose.layout.component.image.RemoteImage
import br.com.zup.nimbus.compose.layout.component.screen.Screen
import br.com.zup.nimbus.compose.layout.component.scroll.ScrollView
import br.com.zup.nimbus.compose.layout.component.text.Text
import br.com.zup.nimbus.compose.ui.NimbusComposeUILibrary

val layoutUI = NimbusComposeUILibrary(LIBRARY_NAME)
    .addComponent(ComponentNames.COLUMN) @Composable { Column(it) }
    .addComponent(ComponentNames.FLOW_COLUMN) @Composable { FlowColumn(it) }
    .addComponent(ComponentNames.FLOW_ROW) @Composable { FlowRow(it) }
    .addComponent(ComponentNames.LIFECYCLE) @Composable { Lifecycle(it) }
    .addComponent(ComponentNames.LOCAL_IMAGE) @Composable { LocalImage(it) }
    .addComponent(ComponentNames.POSITIONED) @Composable { Positioned(it) }
    .addComponent(ComponentNames.REMOTE_IMAGE) @Composable { RemoteImage(it) }
    .addComponent(ComponentNames.ROW) @Composable { Row(it) }
    .addComponent(ComponentNames.SCREEN) @Composable { Screen(it) }
    .addComponent(ComponentNames.SCROLL_VIEW) @Composable { ScrollView(it) }
    .addComponent(ComponentNames.STACK) @Composable { Stack(it) }
    .addComponent(ComponentNames.TEXT) @Composable { Text(it) }
    .addComponent(ComponentNames.TOUCHABLE) @Composable { Touchable(it) }
    .addComponent(ComponentNames.LAZY_ROW) @Composable { LazyRow(it) }
    .addComponent(ComponentNames.LAZY_COLUMN) @Composable { LazyColumn(it) }
