package br.com.zup.nimbus.store.nimbus

import br.com.zup.nimbus.compose.layout.layoutUI
import br.zup.com.nimbus.compose.Nimbus
import br.zup.com.nimbus.compose.NimbusMode
import br.com.zup.nimbus.store.BuildConfig

val nimbus = Nimbus(
    baseUrl = "http://10.0.2.2:3000",
    ui = listOf(layoutUI, storeUI),
    mode = if (BuildConfig.DEBUG) NimbusMode.Development else NimbusMode.Release,
)
