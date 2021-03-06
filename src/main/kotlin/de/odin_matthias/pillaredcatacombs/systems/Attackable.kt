package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.commands.Attack
import de.odin_matthias.pillaredcatacombs.commands.Destroy
import de.odin_matthias.pillaredcatacombs.extensions.combatStats
import de.odin_matthias.pillaredcatacombs.extensions.isPlayer
import de.odin_matthias.pillaredcatacombs.extensions.whenHasNoHealthLeft
import de.odin_matthias.pillaredcatacombs.functions.logGameEvent
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import kotlin.math.max


object Attackable : BaseFacet<GameContext>() {
    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(Attack::class) { (context, attacker, target) ->
        if (attacker.isPlayer || target.isPlayer) {
            val damage = max(0, attacker.combatStats.attackValue - target.combatStats.defenseValue)
            val finalDamage = (Math.random() * damage).toInt() + 1
            target.combatStats.hp -= finalDamage

            logGameEvent("The $attacker hits the $target for $finalDamage!")


            target.whenHasNoHealthLeft {
                target.executeCommand(Destroy(
                        context = context,
                        source = attacker,
                        target = target,
                        cause = "a vicious blow"))
            }

            Consumed
        } else Pass
    }
}