package de.odin_matthias.pillaredcatacombs.game

import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.builders.EntityFactory
import de.odin_matthias.pillaredcatacombs.config.GameConfig
import de.odin_matthias.pillaredcatacombs.config.GameConfig.FUNGI_PER_LEVEL
import de.odin_matthias.pillaredcatacombs.config.GameConfig.GHOULS_PER_LEVEL
import de.odin_matthias.pillaredcatacombs.config.GameConfig.STONE_MASK_FRAGMENTS_PER_LEVEL
import de.odin_matthias.pillaredcatacombs.config.GameConfig.WORLD_SIZE
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.world.WorldBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.impl.Size3D


class GameBuilder(val worldSize: Size3D) {
    private val visibleSize = Sizes.create3DSize(
            xLength = GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH,
            yLength = GameConfig.WINDOW_HEIGHT - GameConfig.LOG_AREA_HEIGHT,
            zLength = 1)

    val world = WorldBuilder(worldSize)
            .makeCatacombs()
            .build(visibleSize = visibleSize)

    fun buildGame(): Game {
        prepareWorld()

        val player = addPlayer()
        addFungi()
        addGhouls()
        addStoneMaskFragments()

        val game = Game.create(
                player = player,
                world = world)

        world.addWorldEntity(EntityFactory.newFogOfWar(game))

        return game
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize().zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        return EntityFactory.newPlayer().addToWorld(
                atLevel = GameConfig.CATACOMB_LEVELS - 1,
                atArea = world.visibleSize().to2DSize())
    }

    private fun addFungi() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(FUNGI_PER_LEVEL) {
                EntityFactory.newFungus().addToWorld(level)
            }
        }
    }

    private fun addGhouls() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(GHOULS_PER_LEVEL) {
                EntityFactory.newGhoul().addToWorld(level)
            }
        }
    }

    private fun addStoneMaskFragments() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(STONE_MASK_FRAGMENTS_PER_LEVEL) {
                EntityFactory.newStoneMaskFragment().addToWorld(level)
            }
        }
    }

    private fun <T : EntityType> GameEntity<T>.addToWorld(atLevel: Int, atArea: Size = world.actualSize().to2DSize()): GameEntity<T> {
        world.addAtEmptyPosition(this,
                offset = Positions.default3DPosition().withZ(atLevel),
                size = Size3D.from2DSize(atArea)
        )

        return this
    }


    companion object {
        fun defaultGame() = GameBuilder(
                worldSize = WORLD_SIZE).buildGame()
    }
}