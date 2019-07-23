package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.EnergyLevel
import de.odin_matthias.pillaredcatacombs.attributes.types.energy
import de.odin_matthias.pillaredcatacombs.attributes.types.energyLevel
import de.odin_matthias.pillaredcatacombs.commands.Eat
import de.odin_matthias.pillaredcatacombs.extensions.GameCommand
import de.odin_matthias.pillaredcatacombs.extensions.isPlayer
import de.odin_matthias.pillaredcatacombs.functions.logGameEvent
import de.odin_matthias.pillaredcatacombs.game.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType


object DigestiveSystem : BaseFacet<GameContext>(EnergyLevel::class) {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Eat::class) { (_, entity, food) ->
        entity.energyLevel.currentEnergy += food.energy
        val verb = if (entity.isPlayer) {
            "You eat"
        } else "The $entity eats"
        logGameEvent("$verb the $food.")

        Consumed
    }
}