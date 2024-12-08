package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.io.*;
import java.io.IOException;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

        public Quit() {
            this.commandType = CommandType.QUIT;
        }

        @Override
        public String execute(GameState gameState) throws IOException {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("gamestate.txt"))) {
                    writer.write("player:" + gameState.getPlayer().getName() + "\n");
                    writer.write("map:" + gameState.getMap().getCurrentRoom().getName() + "\n");

                    for (Room room : gameState.getMap().getRooms()) {
                        writer.write("room:" + room.getName() + "," + room.getDescription() + "," + room.isVisited() + "\n");
                        for (Item item : room.getItems()) {
                            writer.write("item:" + item.getName() + "," + item.getDescription() + "," + item.isUsed() + "\n");
                        }
                        for (Feature feature : room.getFeatures()) {
                            if (feature instanceof Container) {
                                Container container = (Container) feature;
                                writer.write("container:" + container.getName() + "," + container.getDescription() + "," + container.isLocked() + "\n");
                            }
                        }
                        for (Exit exit : room.getExits()) {
                            writer.write("exit:" + exit.getName() + "," + exit.getDescription() + "," + exit.getDestination() + "," + exit.isLocked() + "\n");
                        }
                    }

                    for (Equipment equipment : gameState.getPlayer().getEquipment()) {
                        UseInformation useInfo = equipment.getUseInformation();
                        writer.write("equipment:" + equipment.getName() + "," + equipment.getDescription() + "," + equipment.isUsed() + "," +
                                useInfo.isUsed() + "," + useInfo.getAction() + "," + useInfo.getTarget() + "," + useInfo.getResult() + "," + useInfo.getMessage() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return "You have quit the game. Thank you for playing!\n";


        }

        @Override
        public String toString() {
            return "The quit command.";
        }
 
}
