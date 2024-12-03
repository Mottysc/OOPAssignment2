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
        if (gameState.getMap().getCurrentRoom().hasItem(value)) {
            // Check if the player already has the item
            if (gameState.getPlayer().hasItem(value)) {
                return "You already have a " + value + ".";
            } else {
                // Add the item to the player's inventory
                Item item = gameState.getMap().getCurrentRoom().getItemByName(value);
                gameState.getPlayer().addItem(item);
                // Remove the item from the current room
                gameState.getMap().getCurrentRoom().getItems().remove(item);
                return "You picked up the " + value + ".";
            }
        } else {
            return "There is no " + value + " here.";
        }
    }
   
}
