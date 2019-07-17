package de.odin_matthias.pillaredcatacombs.functions

import de.odin_matthias.pillaredcatacombs.events.GameLogEvent
import org.hexworks.zircon.internal.Zircon


fun logGameEvent(text: String) {
    Zircon.eventBus.publish(GameLogEvent(text))
}