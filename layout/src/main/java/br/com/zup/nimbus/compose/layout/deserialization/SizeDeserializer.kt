package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.AdaptiveSize
import br.com.zup.nimbus.compose.layout.style.model.Size
import br.com.zup.nimbus.compose.layout.utils.Either
import br.zup.com.nimbus.compose.ComponentData
import com.zup.nimbus.processor.TypeDeserializer

fun handleWidthDeserialization(rawWidth: Any?): Either<AdaptiveSize, Double> {
    var width: Either<AdaptiveSize, Double> = Either.Left(AdaptiveSize.FIT_CONTENT)
    when (rawWidth) {
        is String -> width = Either.Left(AdaptiveSize.fromName(rawWidth))
        is Double -> width = Either.Right(rawWidth)
        is Int -> width = Either.Right(rawWidth.toDouble())
        is Float -> width = Either.Right(rawWidth.toDouble())
    }
    return width
}

fun handleHeightDeserialization(rawHeight: Any?): Either<AdaptiveSize, Double> {
    var height: Either<AdaptiveSize, Double> = Either.Left(AdaptiveSize.FIT_CONTENT)
    when (rawHeight) {
        is String -> height = Either.Left(AdaptiveSize.fromName(rawHeight))
        is Double -> height = Either.Right(rawHeight)
        is Int -> height = Either.Right(rawHeight.toDouble())
        is Float -> height = Either.Right(rawHeight.toDouble())
    }
    return height
}

object SizeDeserializer: TypeDeserializer<Size> {
    override fun deserialize(data: Any): Size {
        data as ComponentData
        return Size(
            handleWidthDeserialization(data.node.properties?.get("width")),
            handleHeightDeserialization(data.node.properties?.get("height")),
            data.node.properties?.get("minWidth") as? Double,
            data.node.properties?.get("minHeight") as? Double,
            data.node.properties?.get("maxWidth") as? Double,
            data.node.properties?.get("maxHeight") as? Double,
            (data.node.properties?.get("clipped") as? Boolean == true)
        )
    }
}
