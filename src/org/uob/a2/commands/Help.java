package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.ArrayList;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {
    private ArrayList<String> validCommandNames = new ArrayList<>();
    private String value;

    public Help() {
        this.commandType = CommandType.HELP;
        this.value = null;
        validCommandNames.add("move");
        validCommandNames.add("look");
        validCommandNames.add("get");
        validCommandNames.add("drop");
        validCommandNames.add("use");
        validCommandNames.add("status");
        validCommandNames.add("help");
        validCommandNames.add("combine");
        validCommandNames.add("quit");

    }
    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
    }

    @Override
    public String execute(GameState gameState) {
        if (this.value != null) {
            boolean validCommandName = false;
            for (String command : validCommandNames) {
                if (command.equalsIgnoreCase(value)) {
                    validCommandName = true;
                }
            }
            if (!validCommandName) {
                return "No help available for the topic: " + value;
            }
        }
        if (value == null){
            value = "null";
        }
        return switch (value) {
            case "move" ->
                    "The move command allows you to move to a different location in the game.";
            case "look" ->
                    "The look command allows you to inspect your surroundings. You can look at the current room, an exit, a feature, an item, or a piece of equipment.";
            case "get" ->
                    "The get command allows you to pick up an item or a piece of equipment from the current room.";
            case "drop" ->
                    "The drop command allows you to drop an item or a piece of equipment from your inventory into the current room.";
            case "use" ->
                    "The use command allows you to use an item or a piece of equipment in your inventory. You can use an item on its own or with another object.";
            case "status" ->
                    "The status command allows you to check your current status, inventory, score, or the map. You can also get information about a specific item or piece of equipment.";
            case "help" ->
                    "The help command provides you with information on how to play the game.";
            case "combine" ->
                    "The combine command allows you to combine two items or pieces of equipment into a new object.";
            case "quit" ->
                    "The quit command allows you to exit the game. To quit the game, type 'quit'.";
            case "null" ->
                    "To play the game, you can use the following commands:\n" +
                            "• move <exit name>: Move to a different location as defined by an exit’s name.\n" +
                            "• look <room|exit|features>|<item>|<equipment>: Look around the current room, at an exit/feature, or at a specific item/equipment.\n" +
                            "• get <item|equipment>: Pick up an item or equipment from the room.\n" +
                            "• drop <item|equipment>: Drop an item or equipment from your inventory.\n" +
                            "• use <equipment> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item.\n" +
                            "• status <inventory|player|item|equipment|map|score>: Check your current status/inventory, get information about a specific item/equipment, or display the map and your score.\n" +
                            "• help <topic>: Display this help information or get help on a specific command.\n" +
                            "• combine <item1> and <item2>: Combine two items into a new item or equipment.\n" +
                            "• quit: Exit the game.\n";
            default ->
                    "To play the game, you can use the following commands:\n" +
                    "• move <exit name>: Move to a different location as defined by an exit’s name.\n" +
                    "• look <room|exit|features>|<item>|<equipment>: Look around the current room, at an exit/feature, or at a specific item/equipment.\n" +
                    "• get <item|equipment>: Pick up an item or equipment from the room.\n" +
                    "• drop <item|equipment>: Drop an item or equipment from your inventory.\n" +
                    "• use <equipment> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item.\n" +
                    "• status <inventory|player|item|equipment|map|score>: Check your current status/inventory, get information about a specific item/equipment, or display the map and your score.\n" +
                    "• help <topic>: Display this help information or get help on a specific command.\n" +
                    "• combine <item1> and <item2>: Combine two items into a new item or equipment.\n" +
                    "• quit: Exit the game.\n";
        };
    }

    @Override
    public String toString() {
        return "The help command";
    }

}
