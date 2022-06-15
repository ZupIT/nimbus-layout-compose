package br.com.zup.nimbus.compose.layout.configuration

import android.graphics.Bitmap
import androidx.annotation.DrawableRes

interface ImageProvider {

    fun fetchRemote(url: String, onFetch: (Bitmap) -> Unit, onError: (Throwable) -> Unit)

    @DrawableRes
    fun fetchLocal(id: String): Int?
}
