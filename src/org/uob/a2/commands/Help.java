package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

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

    public Help() {
        this.commandType = CommandType.HELP;
    }
    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
    }

    @Override
    public String execute(GameState gameState) {
        boolean validCommandName = false;
        for (CommandType command : CommandType.values()) {
            if (command.name().equalsIgnoreCase(value)) {
                validCommandName = true;
            }
        }
        if (!validCommandName) {
            return "No help available for the topic: " + value;
        }
        return switch (value) {
            case "move" ->
                    "The move command allows you to move to a different location in the game. To move, type 'move' followed by the name of the exit you want to take.";
            case "look" ->
                    "The look command allows you to inspect your surroundings. You can look at the current room, an exit, a feature, an item, or a piece of equipment. To look, type 'look' followed by the name of the object you want to inspect.";
            case "get" ->
                    "The get command allows you to pick up an item or a piece of equipment from the current room. To pick up an item, type 'get' followed by the name of the item you want to take.";
            case "drop" ->
                    "The drop command allows you to drop an item or a piece of equipment from your inventory into the current room. To drop an item, type 'drop' followed by the name of the item you want to remove.";
            case "use" ->
                    "The use command allows you to use an item or a piece of equipment in your inventory. You can use an item on its own or with another object. To use an item, type 'use' followed by the name of the item and the object you want to use it with.";
            case "status" ->
                    "The status command allows you to check your current status, inventory, or score. You can also get information about a specific item or piece of equipment. To check your status, type 'status' followed by the name of the object you want to inspect.";
            case "help" ->
                    "The help command provides you with information on how to play the game. You can get help on a specific command by typing 'help' followed by the name of the command you want to learn more about.";
            case "combine" ->
                    "The combine command allows you to combine two items or pieces of equipment into a new object. To combine items, type 'combine' followed by the names of the two items you want to merge.";
            case "quit" -> "The quit command allows you to exit the game. To quit the game, type 'quit'.";
            default -> "To play the game, you can use the following commands:\n" +
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
