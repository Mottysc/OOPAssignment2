package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {

    public Get(String item) {
        this.commandType = CommandType.GET;
        this.value = item;
    }

    @Override
    public String execute(GameState gameState) {
        // Check if the item is present in the current room
        String itemType = null;
        if (gameState.getMap().getCurrentRoom().hasItem(value)) {
            itemType = "item";
        } else if (gameState.getMap().getCurrentRoom().hasEquipment(value)) {
            itemType = "equipment";
        }
        switch (itemType) {
            case "item":
                if (gameState.getPlayer().hasItem(value)) {
                    return "You already have " + value;
                } else{
                    Item item = gameState.getMap().getCurrentRoom().getItemByName(value);
                    gameState.getPlayer().addItem(item);
                    gameState.getMap().getCurrentRoom().getItems().remove(item);
                    return "You pick up "+value;
                }
            case "equipment":
                if (gameState.getPlayer().hasEquipment(value)) {
                    return "You already have " + value;
                } else{
                    Equipment item = gameState.getMap().getCurrentRoom().getEquipmentByName(value);
                    gameState.getPlayer().addEquipment(item);
                    gameState.getMap().getCurrentRoom().getEquipments().remove(item);
                    return "You pick up "+value;
                }
            case null:
                return "No "+ value + "to get.";
            default:
                return "No "+ value + "to get.";
        }
    }
    @Override
    public String toString() {
        return "Get " + this.value;
    }
}
