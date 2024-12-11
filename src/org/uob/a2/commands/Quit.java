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
        public Quit(String save) {
            this.commandType = CommandType.QUIT;
            this.value = save;
        }

        @Override
        public String execute(GameState gameState){
            if (this.value == "save"){
                saveGame(gameState);
            }
            StringBuilder result = new StringBuilder("Game over:\nYou have quit the game. The current game has been saved in gamestate.txt\nIn your inventory you had:\n");
            if (gameState.getPlayer().getInventory().isEmpty() && gameState.getPlayer().getEquipment().isEmpty()) {
                result.append("Nothing.");
            }
            for (Item item : gameState.getPlayer().getInventory()) {
                result.append(item.getName()).append(": ").append(item.getDescription()).append("\n");
            }
            for (Equipment equipment : gameState.getPlayer().getEquipment()) {
                result.append(equipment.getName()).append(": ").append(equipment.getDescription()).append("\n");
            }
            return result.toString();


        }
        public void saveGame(GameState gameState) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("gamestate.txt"))) {
                writer.write("player:" + gameState.getPlayer().getName() + "\n");
                writer.write("map:" + gameState.getMap().getCurrentRoom().getId() + "\n");

                for (Room room : gameState.getMap().getRooms()) {
                    writer.write("room:" + room.getId() + "," + room.getName() + "," + room.getDescription() + "," + room.getHidden() + "\n");
                    for (Item item : room.getItems()) {
                        writer.write("item:" + item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getHidden() + "\n");
                    }
                    for (Feature feature : room.getFeatures()) {
                        if (feature instanceof Container container) {
                            writer.write("container:" + container.getId() + "," + container.getName() + "," + container.getDescription() + "," + container.getHidden() + "\n");
                        }
                    }

                    for (Equipment equipment : room.getEquipments()) {
                        UseInformation useInfo = equipment.getUseInformation();
                        writer.write("equipment:" + equipment.getId() + "," + equipment.getName() + "," + equipment.getDescription() + "," + equipment.getHidden() + "," +
                                useInfo.isUsed() + "," + useInfo.getAction() + "," + useInfo.getTarget() + "," + useInfo.getResult() + "," + useInfo.getMessage() + "\n");
                    }
                    for (Exit exit : room.getExits()) {
                        writer.write("exit:" + exit.getId() + "," + exit.getName() + "," + exit.getDescription() + "," + exit.getNextRoom() + "," + exit.getHidden() + (exit.isLocked() ? "," + exit.isLocked(): "") + "\n");
                    }
                }
                for (Item item : gameState.getPlayer().getInventory()) {
                    writer.write("inventory:item," + item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getHidden() + "\n");
                }
                for (Equipment equipment : gameState.getPlayer().getEquipment()) {
                    UseInformation useInfo = equipment.getUseInformation();
                    writer.write("inventory:equipment," + equipment.getId() + "," + equipment.getName() + "," + equipment.getDescription() + "," + equipment.getHidden() + "," +
                            useInfo.isUsed() + "," + useInfo.getAction() + "," + useInfo.getTarget() + "," + useInfo.getResult() + "," + useInfo.getMessage() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return "The quit command.";
        }
 
}
