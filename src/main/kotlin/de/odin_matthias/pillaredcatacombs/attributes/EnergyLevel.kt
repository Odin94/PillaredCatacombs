package de.odin_matthias.pillaredcatacombs.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import kotlin.math.min


class EnergyLevel(initialEnergy: Int,
                  val maxEnergy: Int) : Attribute {

    var currentEnergy: Int
        get() = currentValueProperty.value
        set(value) {
            currentValueProperty.value = min(value, maxEnergy)
        }

    private val currentValueProperty = createPropertyFrom(initialEnergy)
}