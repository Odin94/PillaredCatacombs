package de.odin_matthias.pillaredcatacombs.game

import de.odin_matthias.pillaredcatacombs.attributes.Player
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.world.World
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent


class GameContext(val world: World, val screen: Screen,
                  val uiEvent: UIEvent, val player: GameEntity<Player>) : Context