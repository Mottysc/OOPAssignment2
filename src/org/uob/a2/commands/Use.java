package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.ArrayList;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {
    private String equipmentName;
    private String target;
    public Use(String equipmentName, String target) {
        this.commandType = CommandType.USE;
        this.equipmentName = equipmentName;
        this.target = target;
    }

    public String execute(GameState gameState) {
        Equipment equipment = gameState.getPlayer().getEquipment(equipmentName);
        GameObject targetObject = null;
        if (equipment == null) {
            return "You do not have " + equipmentName;
        }
        ArrayList<GameObject> targetObjects = gameState.getMap().getCurrentRoom().getAll();
        boolean foundValid = false;
        for (GameObject obj : targetObjects) {
            if (obj.getName().equals(target)) {
                if (equipment.getUseInformation().getTarget().equals(targetObject.getId())) {
                    foundValid = true;
                    targetObject = obj;
                } else {
                    return "You cannot use the " + equipmentName + " on the " + target + ".";
                }
            }
        }
        if (!foundValid) {
            return "There is no " + target + " in the room.";
        }
        else {
            return equipment.use(targetObject, gameState);
        }
    }

    @Override
    public String toString() {
        return "Use " + equipmentName + " on " + target;
    }
}
