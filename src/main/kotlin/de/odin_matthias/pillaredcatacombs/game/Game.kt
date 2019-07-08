package de.odin_matthias.pillaredcatacombs.game

import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.world.World


class Game(val world: World,
           val player: GameEntity<Player>) {
    companion object {
        fun create(player: GameEntity<Player>,
                   world: World) = Game(
                world = world,
                player = player
        )
    }
}