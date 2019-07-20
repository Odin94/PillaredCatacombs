package de.odin_matthias.pillaredcatacombs.entities

import de.odin_matthias.pillaredcatacombs.attributes.FogOfWarTypeType
import de.odin_matthias.pillaredcatacombs.blocks.GameTileRepository
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.game.Game
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.base.BaseEntity
import org.hexworks.zircon.api.Layers
import org.hexworks.zircon.api.graphics.Layer
import java.util.concurrent.ConcurrentHashMap


class FogOfWar(game: Game) : BaseEntity<FogOfWarTypeType, GameContext>(FogOfWarTypeType) {
    private val world = game.world
    private val player = game.player
    private val size = game.world.actualSize()

    private val fowPerLevel = ConcurrentHashMap<Int, Layer>().also { fows ->
        repeat(size.zLength) { level ->
            val fow = Layers.newBuilder()
                    .withSize(size.to2DSize())
                    .build()
                    .fill(GameTileRepository.UNREVEALED)
            fows[level] = fow
            world.pushOverlayAt(fow, level)
        }
    }

    init {
        updateFOW()
    }

    override fun update(context: GameContext): Boolean {
        updateFOW()
        return true
    }

    private fun updateFOW() {
        world.findVisiblePositionsFor(player).forEach {
            // set shroud to empty on the FoW overlay so the tiles in the actual game world become visible
            fowPerLevel[player.position.z]?.setTileAt(it, GameTileRepository.EMPTY)
        }
    }
}