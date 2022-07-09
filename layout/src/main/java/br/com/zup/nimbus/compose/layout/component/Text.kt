package br.com.zup.nimbus.compose.layout.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import br.com.zup.nimbus.compose.layout.extensions.color
import br.com.zup.nimbus.compose.layout.model.TextModel

@OptIn(ExperimentalUnitApi::class)
@Composable
internal fun Text(
    model: TextModel,
    modifier: Modifier = Modifier
) {
    Text(modifier = modifier,
        text = model.text ?: "",
        color = model.color?.color ?: Color.Black,
        fontSize = TextUnit((model.size?.toLong() ?: 0L).toFloat(), TextUnitType.Sp),
        fontWeight = model.weight?.toFontWeight())
}

