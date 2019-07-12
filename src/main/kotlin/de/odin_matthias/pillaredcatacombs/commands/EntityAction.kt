package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType

// like a GameCommand except it has a target (not just entity doing action x, but entity doing action x to target y)
interface EntityAction<S : EntityType, T : EntityType> : GameCommand<S> {
    val target: GameEntity<T>

    operator fun component1() = context
    operator fun component2() = source
    operator fun component3() = target
}