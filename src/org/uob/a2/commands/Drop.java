package org.uob.a2.commands;

import org.uob.a2.gameobjects.Equipment;
import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Item;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 *
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
    public Drop(String item) {
        this.commandType = CommandType.DROP;
        this.value = item;
    }

    @Override
    public String execute(GameState gameState) {
        // Check if the player has the item
        String itemType = "null";
        if (gameState.getPlayer().hasItem(value)) {
            itemType = "item";
        } else if (gameState.getPlayer().hasEquipment(value)) {
            itemType = "equipment";
        }
        switch (itemType){
            case "item":
                Item droppedItem = gameState.getPlayer().getItem(value);
                gameState.getPlayer().getInventory().remove(droppedItem);
                gameState.getMap().getCurrentRoom().addItem(droppedItem);
                return "You drop: " + value;
            case "equipment":
                Equipment droppedEquip = gameState.getPlayer().getEquipment(value);
                gameState.getPlayer().getEquipment().remove(droppedEquip);
                gameState.getMap().getCurrentRoom().addEquipment(droppedEquip);
                return "You drop: " + value;
            default:
                return "You cannot drop "+value;
        }
    }
    @Override
    public String toString() {
        return "Drop " + this.value;
    }
}