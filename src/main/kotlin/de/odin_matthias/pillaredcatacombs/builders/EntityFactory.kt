package de.odin_matthias.pillaredcatacombs.builders

import de.odin_matthias.pillaredcatacombs.attributes.EntityPosition
import de.odin_matthias.pillaredcatacombs.attributes.EntityTile
import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.blocks.GameTileRepository
import de.odin_matthias.pillaredcatacombs.game.GameContext
import de.odin_matthias.pillaredcatacombs.systems.InputReceiver
import de.odin_matthias.pillaredcatacombs.systems.Movable
import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType


fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
        Entities.newEntityOfType(type, init)

object EntityFactory {
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(EntityPosition(), EntityTile(GameTileRepository.PLAYER))
        behaviors(InputReceiver)
        facets(Movable)
    }
}