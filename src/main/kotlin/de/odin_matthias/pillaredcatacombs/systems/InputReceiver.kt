package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.commands.MoveTo
import de.odin_matthias.pillaredcatacombs.extensions.GameEntity
import de.odin_matthias.pillaredcatacombs.extensions.position
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent


object InputReceiver : BaseBehavior<GameContext>() {
    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context
        val currentPos = player.position

        if (uiEvent is KeyboardEvent) {
            val newPosition = when (uiEvent.code) {
                KeyCode.KEY_W -> currentPos.withRelativeY(-1)
                KeyCode.KEY_A -> currentPos.withRelativeX(-1)
                KeyCode.KEY_S -> currentPos.withRelativeY(1)
                KeyCode.KEY_D -> currentPos.withRelativeX(1)
                else -> currentPos
            }
            player.executeCommand(MoveTo(context, player, newPosition))
        }
        return true
    }
}