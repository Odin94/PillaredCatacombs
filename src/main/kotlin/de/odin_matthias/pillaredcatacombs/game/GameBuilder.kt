package de.odin_matthias.pillaredcatacombs.game

import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.builders.EntityFactory
import de.odin_matthias.pillaredcatacombs.config.GameConfig
import de.odin_matthias.pillaredcatacombs.config.GameConfig.WORLD_SIZE
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.world.WorldBuilder
import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Sizes
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

        return Game.create(
                player = player,
                world = world)
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize().zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        val player = EntityFactory.newPlayer()
        world.addAtEmptyPosition(player,
                offset = Positions.default3DPosition().withZ(GameConfig.CATACOMB_LEVELS - 1),
                size = world.visibleSize().copy(zLength = 0))

        return player
    }

    companion object {
        fun defaultGame() = GameBuilder(
                worldSize = WORLD_SIZE).buildGame()
    }
}