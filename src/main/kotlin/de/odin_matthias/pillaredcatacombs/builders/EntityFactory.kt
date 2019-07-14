package de.odin_matthias.pillaredcatacombs.builders

import de.odin_matthias.pillaredcatacombs.attributes.*
import de.odin_matthias.pillaredcatacombs.blocks.GameTileRepository
import de.odin_matthias.pillaredcatacombs.commands.Attack
import de.odin_matthias.pillaredcatacombs.commands.Attackable
import de.odin_matthias.pillaredcatacombs.commands.Dig
import de.odin_matthias.pillaredcatacombs.commands.FungusGrowth
import de.odin_matthias.pillaredcatacombs.flags.BlockOccupier
import de.odin_matthias.pillaredcatacombs.game.GameContext
import de.odin_matthias.pillaredcatacombs.systems.CameraMover
import de.odin_matthias.pillaredcatacombs.systems.Diggable
import de.odin_matthias.pillaredcatacombs.systems.InputReceiver
import de.odin_matthias.pillaredcatacombs.systems.Movable
import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType


fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
        Entities.newEntityOfType(type, init)

object EntityFactory {
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(EntityPosition(), EntityTile(GameTileRepository.PLAYER), EntityActions(Dig::class, Attack::class))
        behaviors(InputReceiver)
        facets(Movable, CameraMover)
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(EntityPosition(), BlockOccupier, EntityTile(GameTileRepository.WALL))
        behaviors()
        facets(Diggable)
    }

    fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
        attributes(EntityPosition(), BlockOccupier, EntityTile(GameTileRepository.FUNGUS), fungusSpread)
        behaviors(FungusGrowth)
        facets(Attackable)
    }
}