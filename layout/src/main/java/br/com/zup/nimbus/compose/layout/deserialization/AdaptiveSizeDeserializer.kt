package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.AdaptiveSize
import br.com.zup.nimbus.core.deserialization.AnyServerDrivenData
import br.com.zup.nimbus.annotation.Deserializer

@Deserializer
fun deserializeAdaptiveSize(data: AnyServerDrivenData): AdaptiveSize? {
    val sizeString = data.asStringOrNull()?.lowercase()
    if (sizeString == "expand") return AdaptiveSize.Expand
    if (sizeString == "fitcontent") return AdaptiveSize.FitContent
    val sizeDouble = data.asDoubleOrNull()
    return sizeDouble?.let { AdaptiveSize.Fixed(it) }
}
