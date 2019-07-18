package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.entity.EntityType


data class MoveDown(override val context: GameContext,
                    override val source: GameEntity<EntityType>) : GameCommand<EntityType>