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
            Item item1 = gameState.getPlayer().getItem(firstItem);
            Item item2 = gameState.getPlayer().getItem(secondItem);
            // Check if the items can be combined
            boolean canCombine = false;
            for (Combination combo : gameState.getPlayer().getCombinations()){
                // Allow for the items to be put either way, eg "combine flour with eggs"/"combine eggs with flour"
                if (combo.getItem1().equalsIgnoreCase(item1.getId()) && combo.getItem2().equalsIgnoreCase(item2.getId()) || combo.getItem1().equalsIgnoreCase(item2.getId()) && combo.getItem2().equalsIgnoreCase(item1.getId())){
                    canCombine = true;
                    // Combine the items
                    String newItemId = combo.getResult();
                    // Add the new item to the player's inventory
                    for (Room room : gameState.getMap().getRooms()) {
                        Item createdItem = room.getItem(newItemId);
                        if (createdItem == null) {
                            Equipment createdEquipment = room.getEquipment(newItemId);
                            if (createdEquipment != null) {
                                gameState.getPlayer().addEquipment(createdEquipment);
                                createdEquipment.setHidden(false);
                                // Remove the original items from the player's inventory
                                gameState.getPlayer().getInventory().remove(item1);
                                gameState.getPlayer().getInventory().remove(item2);
                                room.getEquipments().remove(createdEquipment);
                                gameState.getPlayer().addScore(5);
                                return combo.getDescription() + "\n+5 points!";
                            }
                        } else {
                            gameState.getPlayer().addItem(createdItem);
                            createdItem.setHidden(false);
                            // Remove the original items from the player's inventory
                            gameState.getPlayer().getInventory().remove(item1);
                            gameState.getPlayer().getInventory().remove(item2);
                            room.getItems().remove(createdItem);
                            gameState.getPlayer().addScore(5);
                            return combo.getDescription() + "\n+5 points!";
                        }

                    }
                }
            }
            if (!canCombine) {
                return "You cannot combine the " + firstItem + " and " + secondItem + ".";
            }
        }
        else {
            return "You do not have the required items in your inventory.";
        }
        return null;
    }

    @Override
    public String toString() {
        return "Combine " + this.firstItem + " and " + this.secondItem;
    }
}