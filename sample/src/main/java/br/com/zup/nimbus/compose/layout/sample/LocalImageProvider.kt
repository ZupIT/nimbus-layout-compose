package br.com.zup.nimbus.compose.layout.sample

import br.com.zup.nimbus.compose.layout.configuration.DefaultImageProvider

internal class LocalImageProvider: DefaultImageProvider() {
    override fun fetchLocal(id: String): Int? = when (id) {
        "nimbus-local" -> R.drawable.nimbus_local
        "placeholder-img" -> R.drawable.placeholder_img
        else -> null
    }
}
