package br.com.zup.nimbus.compose.layout.model

import androidx.compose.runtime.Composable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

typealias Component = @Composable() () -> Unit
typealias Action = (Any?) -> Unit

internal interface BaseImage : Size, WithAccessibility {
    val scale: ImageScale?
}

internal interface WithChildStretch {
    var hasChildStretch: Boolean?
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
    const val NIMBUS_SCROLL_VIEW = "layout:scrollView"
    const val NIMBUS_SCREEN = "layout:screen"
    const val NIMBUS_TEXT = "layout:text"
    const val NIMBUS_LIFECYCLE = "layout:lifecycle"
}
