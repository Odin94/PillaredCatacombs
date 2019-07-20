package de.odin_matthias.pillaredcatacombs.builders

import de.odin_matthias.pillaredcatacombs.attributes.*
import de.odin_matthias.pillaredcatacombs.blocks.GameTileRepository
import de.odin_matthias.pillaredcatacombs.commands.*
import de.odin_matthias.pillaredcatacombs.entities.FogOfWar
import de.odin_matthias.pillaredcatacombs.flags.BlockOccupier
import de.odin_matthias.pillaredcatacombs.flags.VisionBlocker
import de.odin_matthias.pillaredcatacombs.game.Game
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
    fun newFogOfWar(game: Game) = FogOfWar(game)

    fun newPlayer() = newGameEntityOfType(Player) {
        val playerCombatStats = CombatStats.create(maxHp = 100, attackValue = 10, defenseValue = 5)

        attributes(EntityPosition(), EntityTile(GameTileRepository.PLAYER), EntityActions(Dig::class, Attack::class), playerCombatStats, Vision(9))
        behaviors(InputReceiver)
        facets(Movable, CameraMover, StairClimber, StairDescender)
    }

    fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
        val fungusCombatStats = CombatStats.create(maxHp = 10, attackValue = 1, defenseValue = 0)

        attributes(EntityPosition(), BlockOccupier, EntityTile(GameTileRepository.FUNGUS), fungusSpread, fungusCombatStats)
        behaviors(FungusGrowth)
        facets(Attackable, Destructible)
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(EntityPosition(), BlockOccupier, EntityTile(GameTileRepository.WALL), VisionBlocker)
        behaviors()
        facets(Diggable)
    }

    fun newStairsDown() = newGameEntityOfType(StairsDown) {
        attributes(EntityPosition(), EntityTile(GameTileRepository.STAIRS_DOWN))
        behaviors()
        facets()
    }

    fun newStairsUp() = newGameEntityOfType(StairsUp) {
        attributes(EntityPosition(), EntityTile(GameTileRepository.STAIRS_UP))
        behaviors()
        facets()
    }
}