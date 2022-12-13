package br.com.zup.nimbus.compose.layout.configuration

import android.graphics.Bitmap
import androidx.annotation.DrawableRes

interface ImageProvider {
    suspend fun fetchRemote(url: String): Bitmap

    @DrawableRes
    fun fetchLocal(id: String): Int?
}
