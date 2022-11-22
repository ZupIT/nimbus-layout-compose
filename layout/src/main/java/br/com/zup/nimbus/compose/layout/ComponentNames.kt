package br.com.zup.nimbus.compose.layout

const val LIBRARY_NAME = "layout"

internal object ComponentNames {
    const val ROW = "row"
    const val FLOW_ROW = "flowRow"
    const val COLUMN = "column"
    const val FLOW_COLUMN = "flowColumn"
    const val TOUCHABLE = "touchable"
    const val LAZY_ROW = "lazyRow"
    const val LAZY_COLUMN = "lazyColumn"
    const val POSITIONED = "positioned"
    const val STACK = "stack"
    const val REMOTE_IMAGE = "remoteImage"
    const val LOCAL_IMAGE = "localImage"
    const val SCROLL_VIEW = "scrollView"
    const val SCREEN = "screen"
    const val TEXT = "text"
    const val LIFECYCLE = "lifecycle"
}

fun getFullNameOfComponent(name: String): String {
    return "$LIBRARY_NAME:$name"
}
