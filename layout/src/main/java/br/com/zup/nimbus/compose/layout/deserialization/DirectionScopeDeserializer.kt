package br.com.zup.nimbus.compose.layout.deserialization

import br.com.zup.nimbus.compose.layout.style.model.DirectionScope
import br.zup.com.nimbus.compose.ComponentData
import br.zup.com.nimbus.compose.TypeDeserializer
import com.zup.nimbus.core.deserialization.ComponentDeserializer
import com.zup.nimbus.core.scope.Scope
import com.zup.nimbus.core.tree.ServerDrivenNode
import com.zup.nimbus.core.tree.dynamic.node.DynamicNode

private fun closestNonPolymorphicNode(scope: Scope?): ServerDrivenNode? {
    return if (scope is DynamicNode && !scope.polymorphic) scope
        else scope?.parent?.let { closestNonPolymorphicNode(it) }
}

object DirectionScopeDeserializer: TypeDeserializer<DirectionScope> {
    override fun deserialize(
        properties: ComponentDeserializer,
        data: ComponentData,
        name: String
    ): DirectionScope {
        val parent = closestNonPolymorphicNode(data.node.parent)
        // TODO: expose this logic to the user. It must be possible to create non-leaf custom
        //  components that doesn't ruin the Size properties.
        return when(parent?.component) {
            "layout:row", "layout:flowRow" -> DirectionScope.Row
            "layout:stack" -> DirectionScope.Stack
            else -> DirectionScope.Column
        }
    }
}
