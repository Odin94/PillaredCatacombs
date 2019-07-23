package de.odin_matthias.pillaredcatacombs.systems

import de.odin_matthias.pillaredcatacombs.attributes.inventory
import de.odin_matthias.pillaredcatacombs.commands.DropItem
import de.odin_matthias.pillaredcatacombs.commands.InspectInventory
import de.odin_matthias.pillaredcatacombs.config.GameConfig
import de.odin_matthias.pillaredcatacombs.game.GameContext
import de.odin_matthias.pillaredcatacombs.view.fragment.InventoryFragment
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.component.modal.EmptyModalResult


object InventoryInspector : BaseFacet<GameContext>() {
    val DIALOG_SIZE = Sizes.create(33, 14)

    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(InspectInventory::class) { (context, itemHolder, position) ->
        val screen = context.screen

        val panel = Components.panel()
                .withSize(DIALOG_SIZE)
                .wrapWithBox(true)
                .wrapWithShadow(true)
                .withTitle("Inventory")
                .build()

        val fragment = InventoryFragment(itemHolder.inventory, DIALOG_SIZE.width - 3) { item ->
            itemHolder.executeCommand(DropItem(context, itemHolder, item, position))
        }
        panel.addFragment(fragment)

        val modal = ModalBuilder.newBuilder<EmptyModalResult>()
                .withParentSize(screen.size)
                .withComponent(panel)
                .build()

        panel.addComponent(Components.button()
                .withText("close")
                .withAlignmentWithin(panel, ComponentAlignment.BOTTOM_LEFT)
                .build().apply {
                    onComponentEvent(ComponentEventType.ACTIVATED) {
                        modal.close(EmptyModalResult)
                        Processed
                    }
                }
        )

        modal.applyColorTheme(GameConfig.THEME)
        screen.openModal(modal)

        Consumed
    }
}