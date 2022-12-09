package br.com.zup.nimbus.store.nimbus

import br.com.zup.nimbus.compose.layout.layoutUI
import br.com.zup.nimbus.compose.Nimbus
import br.com.zup.nimbus.compose.NimbusMode
import br.com.zup.nimbus.store.BuildConfig

val nimbus = Nimbus(
    baseUrl = "http://192.168.1.181:3000",
    ui = listOf(layoutUI, storeUI),
    mode = if (BuildConfig.DEBUG) NimbusMode.Development else NimbusMode.Release,
)
