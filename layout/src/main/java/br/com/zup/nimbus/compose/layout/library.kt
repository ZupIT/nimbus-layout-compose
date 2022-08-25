package br.com.zup.nimbus.compose.layout
import androidx.compose.runtime.Composable
import br.com.zup.nimbus.compose.layout.component.Column
import br.com.zup.nimbus.compose.layout.component.FlowColumn
import br.com.zup.nimbus.compose.layout.component.FlowRow
import br.com.zup.nimbus.compose.layout.component.Lifecycle
import br.com.zup.nimbus.compose.layout.component.Positioned
import br.com.zup.nimbus.compose.layout.component.Row
import br.com.zup.nimbus.compose.layout.component.Screen
import br.com.zup.nimbus.compose.layout.component.Stack
import br.com.zup.nimbus.compose.layout.component.Touchable
import br.com.zup.nimbus.compose.layout.component.image.LocalImage
import br.com.zup.nimbus.compose.layout.component.image.RemoteImage
import br.com.zup.nimbus.compose.layout.component.scroll.ScrollView
import br.com.zup.nimbus.compose.layout.component.text.Text
import br.zup.com.nimbus.compose.ComponentLibrary

val layoutComponents = ComponentLibrary(LIBRARY_NAME)
    .add(ComponentNames.COLUMN) @Composable { Column(it) }
    .add(ComponentNames.FLOW_COLUMN) @Composable { FlowColumn(it) }
    .add(ComponentNames.FLOW_ROW) @Composable { FlowRow(it) }
    .add(ComponentNames.LIFECYCLE) @Composable { Lifecycle(it) }
    .add(ComponentNames.LOCAL_IMAGE) @Composable { LocalImage(it) }
    .add(ComponentNames.POSITIONED) @Composable { Positioned(it) }
    .add(ComponentNames.REMOTE_IMAGE) @Composable { RemoteImage(it) }
    .add(ComponentNames.ROW) @Composable { Row(it) }
    .add(ComponentNames.SCREEN) @Composable { Screen(it) }
    .add(ComponentNames.SCROLL_VIEW) @Composable { ScrollView(it) }
    .add(ComponentNames.STACK) @Composable { Stack(it) }
    .add(ComponentNames.TEXT) @Composable { Text(it) }
    .add(ComponentNames.TOUCHABLE) @Composable { Touchable(it) }
