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
        switch (value){
            case "room":
                StringBuilder room = new StringBuilder();
                room.append(gameState.getMap().getCurrentRoom().getDescription()).append("\n");
                room.append("You can see:\n");
                for (GameObject obj : gameState.getMap().getCurrentRoom().getAll()) {
                    if (obj.getHidden()) {
                        continue;
                    }
                    room.append(obj.getName()).append(" ").append(obj.getDescription()).append("\n");
                }
                return room.toString();
            case "exits":
                StringBuilder exits = new StringBuilder();
                exits.append("The available exits are:\n");
                for (Exit exit : gameState.getMap().getCurrentRoom().getExits()) {
                    if (exit.getHidden()) {
                        continue;
                    }
                    exits.append(exit.getDescription()).append("\n");
                }
                return exits.toString();
            case "features":
                StringBuilder features = new StringBuilder();
                features.append("You also see:\n");
                for (Feature feature : gameState.getMap().getCurrentRoom().getFeatures()) {
                    if (feature.getHidden()) {
                        continue;
                    }
                    features.append(feature.getName()).append(" ").append(feature.getDescription()).append("\n");
                }
                return features.toString();
            default:
                ArrayList<GameObject> allInRoom = gameState.getMap().getCurrentRoom().getAll();
                for (GameObject obj : allInRoom) {
                    if (obj.getHidden()) {
                        continue;
                    }
                    if (obj.getName().equalsIgnoreCase(value)) {
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
                        if (obj.getName().equalsIgnoreCase(value)) {
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
                        if (obj.getName().equalsIgnoreCase(value)) {
                            found = true;
                            item = obj;
                            break;
                        }
                    }
                }
                if (found) {
                    return item.getDescription();
                } else {
                    return "";
                }
        }
    }
    @Override
    public String toString() {
        return "Look " + this.value;
    }
}
