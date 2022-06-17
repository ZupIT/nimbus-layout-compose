package br.com.zup.nimbus.compose.layout.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class Screen(
    val safeArea: SafeArea = SafeArea(),
    val title: String? = null,
    val showBackButton: Boolean? = true,
    // navigationBarItems?: { title: string, image: string }[],
    val children: Component? = null,
)

internal class SafeArea(
    val top: Boolean? = true,
    val bottom: Boolean? = true,
    val trailing: Boolean? = true,
    val leading: Boolean? = true
)
