package de.odin_matthias.pillaredcatacombs.blocks

import de.odin_matthias.pillaredcatacombs.builders.EntityFactory


object GameBlockFactory {

    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock.createWith(EntityFactory.newWall())

    fun stairsDown() = GameBlock.createWith(EntityFactory.newStairsDown())
    fun stairsUp() = GameBlock.createWith(EntityFactory.newStairsUp())

}