@file:Suppress("UNCHECKED_CAST")

package br.com.zup.nimbus.compose.layout

import br.com.zup.nimbus.compose.layout.action.browserAction
import com.zup.nimbus.core.ActionHandler
import com.zup.nimbus.core.render.ActionEvent
internal const val ACTION_LOG_TAG = "NIMBUS_ACTION"
private const val ACTION_OPEN_URL = "openUrl"
val layoutActions: Map<String, ActionHandler> = mapOf(
    ACTION_OPEN_URL to { event: ActionEvent ->
        browserAction(event)
    }
)
