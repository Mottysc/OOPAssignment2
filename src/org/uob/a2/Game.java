package org.uob.a2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {
    public static void main(String[] args) {
        /*
         Setup game state
                Room currentRoom = new Room("1", "Start Room", "This is the starting room.", false);
                Map map = new Map();
                map.addRoom(currentRoom);
                map.setCurrentRoom("1");
                GameState gameState = new GameState(map, new Player("Player"));

                Command help = new Help("move");
        System.out.println(help.execute(gameState));
        */
    try (BufferedReader reader = new BufferedReader(new FileReader("gamestate.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
    GameState gameState = GameStateFileParser.parse("gamestate.txt");
    System.out.println(gameState);
    }
}
