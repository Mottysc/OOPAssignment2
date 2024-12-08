package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 * format example is:
 * player:Hero
 * map:room1
 * room:room1,Living Room,A cozy living room.,false
 * item:item1,Key,A rusty key.,false
 * equipment:equip1,Sword,A sharp blade.,false,open,room1,hiddenTreasure,You opened the treasure!
 * container:chest1,Old Chest,A dusty old chest.,true
 * exit:exit1,North Exit,Leads to the northern room.,room2,false
 */
public class GameStateFileParser {
    private String filename;

    public GameStateFileParser() {
    }
    public static GameState parse(String filename) {
        HashMap<String, ArrayList<String>> parsedData = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    continue;
                }
                String type = parts[0];
                ArrayList<String> values = new ArrayList<>(Arrays.asList(parts[1].split(",")));;
                parsedData.put(type, values);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String playerName = parsedData.containsKey("player") ? parsedData.get("player").get(0) : "Player";
        GameState gameState = new GameState(new Map(), new Player(playerName));

        Room currentEditingRoom = null;
        for (String type : parsedData.keySet()) {
            ArrayList<String> values = parsedData.get(type);

            switch (type) {
                case "map" -> {
                    gameState.getMap().setCurrentRoom(values.get(0));
                }
                case "room" -> {
                    Room room = new Room(values.get(0), values.get(1), values.get(2), Boolean.parseBoolean(values.get(3)));
                    gameState.getMap().addRoom(room);
                    currentEditingRoom = room;
                }
                case "item" -> {
                    Item item = new Item(values.get(0), values.get(1), values.get(2), Boolean.parseBoolean(values.get(3)));
                    currentEditingRoom.addItem(item);
                }
                case "equipment" -> {
                    Equipment equipment = new Equipment(values.get(0), values.get(1), values.get(2), Boolean.parseBoolean(values.get(3)), new UseInformation(Boolean.parseBoolean(values.get(3)), values.get(4), values.get(5), values.get(6), values.get(7)));
                    currentEditingRoom.addEquipment(equipment);
                }
                case "container" -> {
                    Container container = new Container(values.get(0), values.get(1), values.get(2), Boolean.parseBoolean(values.get(3)));
                    currentEditingRoom.addFeature(container);
                }
                case "exit" -> {
                    Exit exit = new Exit(values.get(0), values.get(1), values.get(2), values.get(3), Boolean.parseBoolean(values.get(4)));
                    currentEditingRoom.addExit(exit);
                }
                default -> {
                }
            }
        }

        return gameState;
    }

}
