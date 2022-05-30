package br.com.zup.nimbus.compose.layout.model

import androidx.compose.runtime.Composable

const val COLOR_BLACK = "#000000"

typealias Component = @Composable() () -> Unit
typealias Action = (Any?) -> Unit
