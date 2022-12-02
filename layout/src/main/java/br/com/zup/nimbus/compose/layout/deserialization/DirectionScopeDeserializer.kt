package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.DirectionScope
import br.com.zup.nimbus.compose.deserialization.DeserializationContext
import br.com.zup.nimbus.core.scope.Scope
import br.com.zup.nimbus.core.tree.ServerDrivenNode
import br.com.zup.nimbus.core.tree.dynamic.node.DynamicNode
import br.com.zup.nimbus.annotation.Deserializer

private val defaultValue = DirectionScope.Column

private fun closestNonPolymorphicNode(scope: Scope?): ServerDrivenNode? {
    return if (scope is DynamicNode && !scope.polymorphic) scope
        else scope?.parent?.let { closestNonPolymorphicNode(it) }
}

@Deserializer
fun getDirectionScope(context: DeserializationContext): DirectionScope {
    return context.component?.let { component ->
        val parent = closestNonPolymorphicNode(component.node.parent)
        // TODO: expose this logic to the user. It must be possible to create non-leaf custom
        //  components that doesn't ruin the Size properties.
        when(parent?.component) {
            "layout:lazyRow", "layout:row", "layout:flowRow" -> DirectionScope.Row
            "layout:stack" -> DirectionScope.Stack
            else -> defaultValue
        }
    } ?: defaultValue
}
