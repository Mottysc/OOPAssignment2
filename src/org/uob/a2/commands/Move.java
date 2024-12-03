package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

        public Move(String direction) {
            this.commandType = CommandType.MOVE;
            this.value = direction;
        }

        @Override
        public String execute(GameState gameState) {
            // Check if the specified direction is a valid exit from the current room
            if (gameState.getMap().getCurrentRoom().getExit(value) != null) {
                // Update the player's location to the connected room
                String nextRoom = gameState.getMap().getCurrentRoom().getExit(value).getId();
                gameState.getMap().setCurrentRoom(nextRoom);
                return "You move " + value + ".";
            } else {
                return "You cannot move " + value + " from here.";
            }
        }

        @Override
        public String toString() {
            return "The move command.";
        }
}
