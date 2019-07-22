package de.odin_matthias.pillaredcatacombs.attributes

import de.odin_matthias.pillaredcatacombs.extensions.GameItem
import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.cobalt.datatypes.Maybe


class Inventory(val size: Int) : Attribute {
    private val currentItems = mutableListOf<GameItem>()

    val items: List<GameItem>
        get() = currentItems.toList()

    val isEmpty: Boolean
        get() = currentItems.isEmpty()

    val isFull: Boolean
        get() = currentItems.size >= size

    fun findItemBy(id: Identifier): Maybe<GameItem> {
        return Maybe.ofNullable(items.firstOrNull { it.id == id })
    }

    fun addItem(item: GameItem): Boolean {
        return if (!isFull) {
            currentItems.add(item)
        } else false
    }

    fun removeItem(item: GameItem): Boolean {
        return currentItems.remove(item)
    }
}