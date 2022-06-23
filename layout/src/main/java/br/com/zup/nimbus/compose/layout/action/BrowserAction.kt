package br.com.zup.nimbus.compose.layout.action

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import br.com.zup.nimbus.compose.layout.ACTION_LOG_TAG
import br.zup.com.nimbus.compose.NimbusTheme
import com.zup.nimbus.core.render.ActionEvent

internal fun browserAction(event: ActionEvent) {
    try {
        val url = event.action.properties!!["url"] as String?
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        NimbusTheme.nimbusStaticState?.let {
            ContextCompat.startActivity(it.applicationContext, browserIntent, null)
        }
    } catch (e: Throwable) {
        Log.e(ACTION_LOG_TAG, e.message, e)
    }
}
