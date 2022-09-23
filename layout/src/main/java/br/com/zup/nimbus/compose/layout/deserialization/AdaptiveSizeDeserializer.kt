package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.AdaptiveSize
import br.zup.com.nimbus.compose.ComponentData
import br.zup.com.nimbus.compose.TypeDeserializer
import com.zup.nimbus.core.deserialization.ComponentDeserializer

object AdaptiveSizeDeserializer: TypeDeserializer<AdaptiveSize?> {
    @Suppress("ReturnCount")
    override fun deserialize(
        properties: ComponentDeserializer,
        data: ComponentData,
        name: String,
    ): AdaptiveSize? {
        val sizeString = properties.asStringOrNull(name)?.lowercase()
        if (sizeString == "expand") return AdaptiveSize.Expand
        if (sizeString == "fitcontent") return AdaptiveSize.FitContent
        val sizeDouble = properties.asDoubleOrNull(name)
        return sizeDouble?.let { AdaptiveSize.Fixed(it) }
    }
}
