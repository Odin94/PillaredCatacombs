package de.odin_matthias.pillaredcatacombs.model

import de.odin_matthias.pillaredcatacombs.GameBlock
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.data.impl.Size3D
import org.hexworks.zircon.api.game.GameArea


class World(startingBlocks: Map<Position3D, GameBlock>, visibleSize: Size3D, actualSize: Size3D)
    : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
        .withVisibleSize(visibleSize)
        .withActualSize(actualSize)
        .withDefaultBlock(DEFAULT_BLOCK)
        .withLayersPerBlock(1)
        .build() {

    init {
        startingBlocks.forEach { (pos, block) ->
            setBlockAt(pos, block)
        }
    }

    companion object {
        private val DEFAULT_BLOCK = GameBlockFactory.floor()
    }
}