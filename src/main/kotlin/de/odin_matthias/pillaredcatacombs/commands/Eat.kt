package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.attributes.types.EnergyUser
import de.odin_matthias.pillaredcatacombs.attributes.types.Food
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.game.GameContext


data class Eat(override val context: GameContext,
               override val source: GameEntity<EnergyUser>,
               override val target: GameEntity<Food>) : EntityAction<EnergyUser, Food>