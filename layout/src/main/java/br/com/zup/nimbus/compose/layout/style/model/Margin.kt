package br.com.zup.nimbus.compose.layout.style.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal interface Margin {
    val margin: Double?
    val marginStart: Double?
    val marginEnd: Double?
    val marginTop: Double?
    val marginBottom: Double?
    val marginHorizontal: Double?
    val marginVertical: Double?
}
