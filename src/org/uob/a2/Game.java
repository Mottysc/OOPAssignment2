package org.uob.a2;

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
        // Setup game state
        Room currentRoom = new Room("1", "Start Room", "This is the starting room.", false);
        Map map = new Map();
        map.addRoom(currentRoom);
        map.setCurrentRoom("1");
        GameState gameState = new GameState(map, new Player("Player"));

        // Display the welcome message
        System.out.println("Welcome to the game!");

        // Main game loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Read user input
            System.out.print("> ");
            String input = scanner.nextLine();

            // Parse the input
            Command command = Parser.parse(input);

            // Execute the command
            String output = command.execute(gameState);
            System.out.println(output);


        }

        // Close the scanner
        scanner.close();
    }
}
