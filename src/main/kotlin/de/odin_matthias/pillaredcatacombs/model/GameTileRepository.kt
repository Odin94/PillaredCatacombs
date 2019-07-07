package de.odin_matthias.pillaredcatacombs.model

import de.odin_matthias.pillaredcatacombs.config.GameColors
import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.graphics.Symbols


object GameTileRepository {

    // TODO: replace these character tiles with images / GraphicTiles
    val EMPTY: CharacterTile = Tiles.empty()

    val FLOOR: CharacterTile = Tiles.newBuilder()
            .withCharacter(Symbols.INTERPUNCT) // 1
            .withForegroundColor(GameColors.FLOOR_FOREGROUND) // 2
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND) // 3
            .buildCharacterTile() // 4

    val WALL: CharacterTile = Tiles.newBuilder()
            .withCharacter('#')
            .withForegroundColor(GameColors.WALL_FOREGROUND)
            .withBackgroundColor(GameColors.WALL_BACKGROUND)
            .buildCharacterTile()

}