package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.ArrayList;
import java.util.*;
import java.util.Map;

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
                        inventory.append(item.getName()).append("\n");
                    }
                    for (Equipment equipment : gameState.getPlayer().getEquipment()) {
                        inventory.append(equipment.getName()).append("\n");
                    }
                    return inventory.toString();
                case "player":
                    StringBuilder player = new StringBuilder("Player: " + gameState.getPlayer().getName() + "\n");
                    player.append("Score:" + gameState.getPlayer().getScore() + "\n");
                    return player.toString();
                case "map":
                    ArrayList<Room> rooms = (gameState.getMap().getRooms());
                    return generateMap(gameState);
                case "score":
                    return "Your current score is: " + gameState.getPlayer().getScore();
                default:
                    // Check if the player has the specified item in their inventory
                    String itemType = "null";
                    if (gameState.getPlayer().hasItem(value)) {
                        itemType = "item";
                    } else if (gameState.getPlayer().hasEquipment(value)) {
                        itemType = "equipment";
                    }
                    switch (itemType) {
                        case "item":
                            Item item = gameState.getPlayer().getItem(value);
                            return item.getName() + ": " + item.getDescription();
                        case "equipment":
                            Equipment equip = gameState.getPlayer().getEquipment(value);
                            return equip.getName() + ": " + equip.getDescription();
                        default:
                            return "";
                    }
            }
        } else {
            // Display the player's inventory
            StringBuilder inventory = new StringBuilder();
            inventory.append("Player: " + gameState.getPlayer().getName() + "\n");
            inventory.append("Score: " + gameState.getPlayer().getScore() + "\n");
            inventory.append("Inventory:\n");
            for (Item item : gameState.getPlayer().getInventory()) {
                inventory.append(item.getName() + "\n");
            }
            for (Equipment equipment : gameState.getPlayer().getEquipment()) {
                inventory.append(equipment.getName() + "\n");
            }
            return inventory.toString();
        }
    }
    public static String generateMap(GameState gameState) {
        ArrayList<Room> rooms = gameState.getMap().getRooms();
        int gridSize = 6; // Define the size of the grid
        String[][] grid = new String[gridSize][gridSize];
        for (String[] row : grid) {
            java.util.Arrays.fill(row, " "); // Fill grid with empty spaces
        }

        // Start in the center of the grid
        int centerX = gridSize / 2;
        int centerY = gridSize / 2;

        HashMap<String, int[]> roomCoordinates = new HashMap<>();
        Queue<Room> toVisit = new LinkedList<>();
        ArrayList<String> visited = new ArrayList<>();

        roomCoordinates.put(rooms.get(0).getId(), new int[]{centerX, centerY});
        grid[centerX][centerY] = rooms.get(0).getId();
        toVisit.add(rooms.get(0));
        visited.add(rooms.get(0).getId());

        while (!toVisit.isEmpty()) {
            Room currentRoom = toVisit.poll();
            int[] currentPosition = roomCoordinates.get(currentRoom.getId());
            int x = currentPosition[0];
            int y = currentPosition[1];

            for (Exit exit : currentRoom.getExits()) {
                if (!visited.contains(exit.getNextRoom())) {
                    int newX = x, newY = y;
                    switch (exit.getName().toLowerCase()) {
                        case "north" -> newX = x - 1;
                        case "south" -> newX = x + 1;
                        case "east" -> newY = y + 1;
                        case "west" -> newY = y - 1;
                    }

                    roomCoordinates.put(exit.getNextRoom(), new int[]{newX, newY});
                    grid[newX][newY] = exit.getNextRoom();
                    toVisit.add(gameState.getMap().getRooms().stream()
                            .filter(room -> room.getId().equalsIgnoreCase(exit.getNextRoom()))
                            .findFirst().orElse(null));
                    visited.add(exit.getNextRoom());
                }
            }
        }

        // Convert the grid to a string
        StringBuilder map = new StringBuilder();
        for (String[] row : grid) {
            for (String cell : row) {
                if (cell.equalsIgnoreCase(" ")) {
                    map.append(" . "); // Empty cell
                } else {
                    if (gameState.getMap().getCurrentRoom().getId().equalsIgnoreCase(cell)){
                        cell = cell.toUpperCase();
                    }
                    map.append(" ").append(cell.charAt(0)).append(" ");
                }
            }
            map.append("\n"); // Newline for next row
        }

        return map.toString();
    }


    @Override
    public String toString() {
        return "The status command. Status " + this.value;
    }
}
