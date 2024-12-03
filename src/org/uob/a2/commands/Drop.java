package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

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
        if (gameState.getPlayer().hasItem(value)) {
            // Remove the item from the player's inventory
            Item droppedItem = gameState.getPlayer().getItem(value);
            gameState.getPlayer().getInventory().remove(droppedItem);
            // Add the item to the current room
            gameState.getMap().getCurrentRoom().addItem(droppedItem);
            return "You dropped the " + value + ".";
        } else {
            return "You do not have a " + value + " to drop.";
        }
    }
    @Override
    public String toString() {
        return "The drop command";
    }
}
