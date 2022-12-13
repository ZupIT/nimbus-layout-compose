package br.com.zup.nimbus.store.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ExitScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        Text("Exit")
        Counter()
    }
}

@Composable
fun Counter() {
    val (count, setCount) = remember {
        mutableStateOf(0)
    }

    Button(onClick = { setCount(count + 1) }) {
        Text(count.toString())
    }
}
