package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.ArrayList;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {
    public Look(String value) {
        this.commandType = CommandType.LOOK;
        this.value = value;
    }
    @Override
    public String execute(GameState gameState) {
        boolean found = false;
        GameObject item = null;
        ArrayList<GameObject> allInRoom = gameState.getMap().getCurrentRoom().getAll();
        for (GameObject obj : allInRoom) {
            if (obj.getHidden()) {
                continue;
            }
            if (obj.getName().equals(value)) {
                found = true;
                item = obj;
                break;
            }
        }
        if (!found) {
            ArrayList<Item> allInInventory = gameState.getPlayer().getInventory();
            for (GameObject obj : allInInventory) {
                if (obj.getHidden()) {
                    continue;
                }
                if (obj.getName().equals(value)) {
                    found = true;
                    item = obj;
                    break;
                }
            }
        }
        if (!found) {
            ArrayList<Equipment> allEquipment = gameState.getPlayer().getEquipment();
            for (GameObject obj : allEquipment) {
                if (obj.getHidden()) {
                    continue;
                }
                if (obj.getName().equals(value)) {
                    found = true;
                    item = obj;
                    break;
                }
            }
        }
        if (found) {
            return item.getDescription();
        }
        else {
            return "You do not see a " + value + " here.";
        }
    }
}
