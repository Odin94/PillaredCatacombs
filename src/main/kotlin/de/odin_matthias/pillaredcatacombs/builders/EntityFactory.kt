package de.odin_matthias.pillaredcatacombs.builders

import de.odin_matthias.pillaredcatacombs.attributes.*
import de.odin_matthias.pillaredcatacombs.blocks.GameTileRepository
import de.odin_matthias.pillaredcatacombs.commands.Attack
import de.odin_matthias.pillaredcatacombs.commands.Dig
import de.odin_matthias.pillaredcatacombs.entities.FogOfWar
import de.odin_matthias.pillaredcatacombs.flags.BlockOccupier
import de.odin_matthias.pillaredcatacombs.flags.VisionBlocker
import de.odin_matthias.pillaredcatacombs.game.Game
import de.odin_matthias.pillaredcatacombs.game.GameContext
import de.odin_matthias.pillaredcatacombs.systems.*
import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.Tiles


fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
        Entities.newEntityOfType(type, init)

object EntityFactory {
    fun newFogOfWar(game: Game) = FogOfWar(game)

    fun newPlayer() = newGameEntityOfType(Player) {
        val playerCombatStats = CombatStats.create(maxHp = 100, attackValue = 10, defenseValue = 5)

        attributes(EntityPosition(),
                BlockOccupier,
                EntityTile(GameTileRepository.PLAYER),
                EntityActions(Dig::class, Attack::class),
                playerCombatStats,
                Vision(9),
                Inventory(10))

        behaviors(InputReceiver)
        facets(Movable, CameraMover, StairClimber, StairDescender, Attackable, Destructible, ItemPicker)
    }

    fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
        val fungusCombatStats = CombatStats.create(maxHp = 10, attackValue = 0, defenseValue = 0)

        attributes(EntityPosition(), BlockOccupier, EntityTile(GameTileRepository.FUNGUS), fungusSpread, fungusCombatStats)
        behaviors(FungusGrowth)
        facets(Attackable, Destructible)
    }

    fun newGhoul() = newGameEntityOfType(Ghoul) {
        val ghoulCombatStats = CombatStats.create(maxHp = 5, attackValue = 2, defenseValue = 1)

        attributes(EntityPosition(), BlockOccupier, EntityTile(GameTileRepository.GHOUL), ghoulCombatStats, EntityActions(Attack::class))
        behaviors(Wanderer)
        facets(Movable, Attackable, Destructible)
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

    fun newStoneMaskFragment() = newGameEntityOfType(StoneMaskFragment) {
        val stoneMaskFragmentIcon = ItemIcon(Tiles.newBuilder()
                .withName("white fragment")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile())

        attributes(stoneMaskFragmentIcon, EntityPosition(), EntityTile(GameTileRepository.STONE_MASK_FRAGMENT))
    }
}