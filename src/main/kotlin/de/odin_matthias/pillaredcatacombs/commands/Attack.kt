package de.odin_matthias.pillaredcatacombs.commands

import de.odin_matthias.pillaredcatacombs.attributes.Combatant
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.game.GameContext


data class Attack(override val context: GameContext,
                  override val source: GameEntity<Combatant>,
                  override val target: GameEntity<Combatant>) : EntityAction<Combatant, Combatant>