package de.odin_matthias.pillaredcatacombs.model

import de.odin_matthias.pillaredcatacombs.GameBlock


object GameBlockFactory {

    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock(GameTileRepository.WALL)

}