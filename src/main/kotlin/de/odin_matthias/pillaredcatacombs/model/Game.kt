package de.odin_matthias.pillaredcatacombs.model

import de.odin_matthias.pillaredcatacombs.config.GameConfig
import org.hexworks.zircon.api.data.impl.Size3D


class Game(val world: World) {
    companion object {
        fun create(worldSize: Size3D = GameConfig.WORLD_SIZE,
                   visibleSize: Size3D = GameConfig.WORLD_SIZE) =
                Game(WorldBuilder(worldSize)
                        .makeCatacombs()
                        .build(visibleSize))
    }
}