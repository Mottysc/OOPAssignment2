package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.io.*;
import java.io.IOException;
import java.util.stream.Collectors;

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
                    writer.write("map:" + gameState.getMap().getRooms().stream().map(Room::getName).collect(Collectors.joining(",")) + "\n");

                    for (Room room : gameState.getMap().getRooms()) {
                        writer.write("room:" + room.getId() + "," + room.getName() + "," + room.getDescription() + "\n");
                        for (Item item : room.getItems()) {
                            writer.write("item:" + item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getHidden() + "\n");
                        }
                        for (Feature feature : room.getFeatures()) {
                            if (feature instanceof Container) {
                                Container container = (Container) feature;
                                writer.write("container:" + container.getId() + "," + container.getName() + "," + container.getDescription() + "\n");
                            }
                        }
                        for (Exit exit : room.getExits()) {
                            writer.write("exit:" + exit.getId() + "," + exit.getName() + "," + exit.getDescription() + "," + exit.getNextRoom() + "\n");
                        }
                    }

                    for (Equipment equipment : gameState.getPlayer().getEquipment()) {
                        UseInformation useInfo = equipment.getUseInformation();
                        writer.write("equipment:" + equipment.getId()  + "," + equipment.getName() + "," + equipment.getDescription() + "," +
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
