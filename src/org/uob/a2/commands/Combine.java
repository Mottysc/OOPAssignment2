package org.uob.a2.commands;
import org.uob.a2.gameobjects.*;

public class Combine extends Command {
    protected String firstItem;
    protected String secondItem;

    public Combine(String firstItem, String secondItem) {
        this.commandType = CommandType.COMBINE;
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    @Override
    public String execute(GameState gameState) {
        // Check if the player has the specified items in their inventory
        if (gameState.getPlayer().hasItem(firstItem) && gameState.getPlayer().hasItem(secondItem)) {
            // Retrieve the items from the player's inventory
            Equipment item1 = gameState.getPlayer().getEquipment(firstItem);
            Equipment item2 = gameState.getPlayer().getEquipment(secondItem);
            // Check if the items can be combined
            if (item1.getUseInformation().getTarget().equals(secondItem) && item2.getUseInformation().getTarget().equals(firstItem) && item1.getUseInformation().getAction().equals("combine") && item2.getUseInformation().getAction().equals("combine")) {
                // Combine the items
                String newItemName = item1.getUseInformation().getResult();
                // Remove the original items from the player's inventory
                gameState.getPlayer().getEquipment().remove(item1);
                gameState.getPlayer().getEquipment().remove(item2);
                // Add the new item to the player's inventory
                String itemID = firstItem + secondItem;
                Item newItem = new Item(itemID, newItemName, "A new item created by combining "+firstItem+" and " + secondItem, false);
                gameState.getPlayer().addItem(newItem);
                return item1.getUseInformation().getMessage();
            } else {
                return "You cannot combine the " + firstItem + " and " + secondItem + ".";
            }
        } else {
            return "You do not have the required items in your inventory.";
        }
    }
    }

    @Override
    public String toString() {
        return "Combine " + this.firstItem + " and " + secondItem;
    }