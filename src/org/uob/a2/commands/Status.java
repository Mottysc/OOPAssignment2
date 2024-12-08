package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Item;

import java.util.ArrayList;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 *
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    private String value;

    public Status(String topic) {
        this.commandType = CommandType.STATUS;
        this.value = topic;
    }

    @Override
    public String execute(GameState gameState) {
        // Check if the player has specified a specific item
        if (value != null) {
            switch(value) {
                case "inventory":
                    ArrayList<Item> inv = gameState.getPlayer().getInventory();
                    StringBuilder inventory = new StringBuilder();
                    inventory.append("Inventory:\n");
                    for (Item item : inv) {
                        inventory.append(item.getName() + "\n");
                    }
                    return inventory.toString();
                case "player":
                    StringBuilder player = new StringBuilder("Player: " + gameState.getPlayer().getName() + "\n");
                    player.append("Score:" + gameState.getPlayer().getScore() + "\n");
                    return player.toString();
                case "map":
                    return gameState.getMap().toString();
                case "score":
                    return "Your current score is: " + gameState.getPlayer().getScore();
                default:
                    // Check if the player has the specified item in their inventory
                    if (gameState.getPlayer().hasItem(value)) {
                        // Retrieve the item from the player's inventory
                        Item item = gameState.getPlayer().getItem(value);
                        return item.getName() + ": " + item.getDescription();
                    } else {
                        return "You do not have a " + value + " in your inventory.";
                    }
            }
        } else {
            // Display the player's inventory
            StringBuilder inventory = new StringBuilder();
            inventory.append("Inventory:\n");
            for (Item item : gameState.getPlayer().getInventory()) {
                inventory.append(item.getName() + "\n");
            }
            return inventory.toString();
        }
    }

    @Override
    public String toString() {
        return "The status command.";
    }


}
