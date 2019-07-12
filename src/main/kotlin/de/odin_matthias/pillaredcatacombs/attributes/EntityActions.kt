package de.odin_matthias.pillaredcatacombs.attributes

import de.odin_matthias.pillaredcatacombs.commands.EntityAction
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Attribute
import org.hexworks.amethyst.api.entity.EntityType
import kotlin.reflect.KClass


// using KClass instead of EntityAction as arg because we need to create a new EntityAction every time it is invoked, KClass is a like template for an EntityAction
class EntityActions(private vararg val actions: KClass<out EntityAction<out EntityType, out EntityType>>) : Attribute {
    fun createActionsFor(context: GameContext, source: GameEntity<EntityType>, target: GameEntity<EntityType>):
            Iterable<EntityAction<out EntityType, out EntityType>> {
        return actions.map {
            try {
                // call first constructor of the action - actions can't have a different constructor as first constructor or this will blow up!
                it.constructors.first().call(context, source, target)
            } catch (e: Exception) {
                throw IllegalArgumentException("Can't create EntityAction. Does likely not have proper constructor.")
            }
        }
    }
}
