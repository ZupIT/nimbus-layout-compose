package br.com.zup.nimbus.compose.layout.model

import androidx.compose.runtime.Composable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

typealias Component = @Composable() () -> Unit
typealias Action = (Any?) -> Unit

@JsonIgnoreProperties(ignoreUnknown = true)
internal open class ParentContainerApi: AbstractComponentApi<ParentContainer>(null)

internal interface BaseImage : Size, WithAccessibility {
    val scale: ImageScale?
}

internal interface ChildWithStretch {
    var childWithStretch: Boolean?
}

internal object ComponentNames {
    const val NIMBUS_ROW = "layout:row"
    const val NIMBUS_FLOW_ROW = "layout:flowRow"
    const val NIMBUS_COLUMN = "layout:column"
    const val NIMBUS_FLOW_COLUMN = "layout:flowColumn"
    const val NIMBUS_TOUCHABLE = "layout:touchable"
    const val NIMBUS_POSITIONED = "layout:positioned"
    const val NIMBUS_STACK = "layout:stack"
    const val NIMBUS_REMOTE_IMAGE = "layout:remoteImage"
    const val NIMBUS_LOCAL_IMAGE = "layout:localImage"
    const val NIMBUS_SCROLL_VIEW = "layout:scroll"
    const val NIMBUS_SCREEN = "layout:screen"
    const val NIMBUS_TEXT = "layout:text"
    const val NIMBUS_LIFECYCLE = "layout:lifecycle"
}

internal interface ComponentApi<T> {
    val component: String?
    val properties: T?
}

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AbstractComponentApi<T>(
    override val properties: T?,
    override val component: String? = null,
) : ComponentApi<T>
