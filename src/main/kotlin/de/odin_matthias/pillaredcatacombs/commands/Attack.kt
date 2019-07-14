package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.entity.EntityType


data class Attack(override val context: GameContext,
                  override val source: GameEntity<EntityType>,
                  override val target: GameEntity<EntityType>) : EntityAction<EntityType, EntityType>