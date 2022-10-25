package br.com.zup.nimbus.compose.layout

import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.component.FlowColumn
import br.com.zup.nimbus.compose.layout.component.FlowRow
import br.com.zup.nimbus.compose.layout.component.Lifecycle
import br.com.zup.nimbus.compose.layout.component.Positioned
import br.com.zup.nimbus.compose.layout.component.Stack
import br.com.zup.nimbus.compose.layout.component.Touchable
import br.com.zup.nimbus.compose.layout.component.container.Column
import br.com.zup.nimbus.compose.layout.component.container.Row
import br.com.zup.nimbus.compose.layout.component.image.LocalImage
import br.com.zup.nimbus.compose.layout.component.image.RemoteImage
import br.com.zup.nimbus.compose.layout.component.screen.Screen
import br.com.zup.nimbus.compose.layout.component.scroll.ScrollView
import br.com.zup.nimbus.compose.layout.component.text.Text
import br.zup.com.nimbus.compose.ui.NimbusComposeUILibrary

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
