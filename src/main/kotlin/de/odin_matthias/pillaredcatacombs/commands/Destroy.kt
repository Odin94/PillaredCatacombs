package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.entity.EntityType


data class Destroy(override val context: GameContext,
                   override val source: GameEntity<EntityType>,
                   val target: GameEntity<EntityType>,
                   val cause: String = "natural causes.") : GameCommand<EntityType>