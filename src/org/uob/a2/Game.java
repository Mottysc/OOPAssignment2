package org.uob.a2;


import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 *
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {
    public static void main(String[] args) throws CommandErrorException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the game!");
        System.out.print("Do you want to start a new game or load a saved game? (New/Load)\n>> ");
        String choice = scanner.nextLine();
        GameState game = null;
        switch (choice.toLowerCase().trim()) {
            case "new":
                System.out.println("Starting a new game...");
                game = GameStateFileParser.parse("data/game.txt");
                break;
            case "load":
                System.out.print("Enter the location of the saved file:\n>> ");
                String filename = scanner.nextLine();
                // Open the file
                try {
                    File file = new File(filename);
                    if (!file.exists()) {
                        System.out.println("File not found. Exiting...");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid file. Exiting...");
                    return;
                }
                game = GameStateFileParser.parse(filename);
                System.out.println("Loading a saved game...");
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }
        
        Parser parser = new Parser();
        Tokeniser tokeniser = new Tokeniser();
        while (true) {
            try {
                System.out.print(">> ");
                tokeniser.tokenise(scanner.nextLine());
                Command command = parser.parse(tokeniser.getTokens());
                System.out.println(command.execute(game));

                if (command instanceof Quit) {
                    break;
                }
            }
            catch (CommandErrorException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
