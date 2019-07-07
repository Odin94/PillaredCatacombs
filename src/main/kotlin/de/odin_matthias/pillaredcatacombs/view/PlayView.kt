package de.odin_matthias.pillaredcatacombs.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Button
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.kotlin.onMouseReleased
import org.hexworks.zircon.api.mvc.base.BaseView


class PlayView : BaseView() {
    override val theme = ColorThemes.arc()

    override fun onDock() {
        screen.addComponent(buildLoseButton())
        screen.addComponent(buildWinButton())
    }

    private fun buildLoseButton(): Button {
        val loseButton = Components.button()
                .withAlignmentWithin(screen, ComponentAlignment.LEFT_CENTER)
                .withText("Lose!")
                .wrapSides(false)
                .withBoxType(BoxType.SINGLE)
                .wrapWithShadow()
                .wrapWithBox()
                .build()

        loseButton.onMouseReleased {
            replaceWith(LoseView())
            close()
        }

        return loseButton
    }

    private fun buildWinButton(): Button {
        val winButton = Components.button()
                .withAlignmentWithin(screen, ComponentAlignment.RIGHT_CENTER)
                .withText("Win!")
                .wrapSides(false)
                .withBoxType(BoxType.SINGLE)
                .wrapWithShadow()
                .wrapWithBox()
                .build()

        winButton.onMouseReleased {
            replaceWith(WinView())
            close()
        }

        return winButton
    }
}