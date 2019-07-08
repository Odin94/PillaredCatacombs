package de.odin_matthias.pillaredcatacombs.blocks

import de.odin_matthias.pillaredcatacombs.config.GameColors
import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.graphics.Symbols


object GameTileRepository {

    // TODO: replace these character tiles with images / GraphicTiles
    val EMPTY = Tiles.empty()

    val PLAYER = Tiles.newBuilder()
            .withCharacter('@')
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .withForegroundColor(GameColors.ACCENT_COLOR)
            .buildCharacterTile()

    val FLOOR = Tiles.newBuilder()
            .withCharacter(Symbols.INTERPUNCT)
            .withForegroundColor(GameColors.FLOOR_FOREGROUND)
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .buildCharacterTile()

    val WALL = Tiles.newBuilder()
            .withCharacter('#')
            .withForegroundColor(GameColors.WALL_FOREGROUND)
            .withBackgroundColor(GameColors.WALL_BACKGROUND)
            .buildCharacterTile()

}