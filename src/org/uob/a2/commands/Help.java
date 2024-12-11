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

    private String value;

    public Help() {
        this.commandType = CommandType.HELP;
        this.value = null;
    }
    public Help(String topic) {
        this.commandType = CommandType.HELP;
        this.value = topic;
    }

    @Override
    public String execute(GameState gameState) {
        if (this.value != null) {
            boolean validCommandName = false;

            for (CommandType command : CommandType.values()) {
                if (command.name().equalsIgnoreCase(value)) {
                    validCommandName = true;
                    value = value.toLowerCase();
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
                    "MOVE Command:\nUse the 'move' command to move to a different location in the game. Specify a direction to move in.";
            case "look" ->
                    "LOOK Command:\nThe look command allows you to inspect your surroundings. You can look at the current room, an exit, a feature, an item, or a piece of equipment.";
            case "get" ->
                    "GET Command:\nThe get command allows you to pick up an item or a piece of equipment from the current room.";
            case "drop" ->
                    "DROP Command:\nThe drop command allows you to drop an item or a piece of equipment from your inventory into the current room.";
            case "use" ->
                    "USE Command:\nThe use command allows you to use an item or a piece of equipment in your inventory. You can use an item on its own or with another object.";
            case "status" ->
                    "STATUS Command:\nThe status command allows you to check your current status, inventory, score, or the map. You can also get information about a specific item or piece of equipment.";
            case "help" ->
                    "HELP Command:\nThe help command provides you with information on how to play the game.";
            case "combine" ->
                    "COMBINE Command:\nThe combine command allows you to combine two items or pieces of equipment into a new object.";
            case "quit" ->
                    "QUIT [save] Command:\nThe quit command allows you to exit the game. To quit the game, type 'quit'. Include 'save' to save your progress.";
            case "null" ->
                    "Welcome to the game! You can use the following commands:\n" +
                            "- MOVE <exit name>: Move to a different location as defined by an exit’s name.\n" +
                            "- LOOK <room|exit|features>|<item>|<equipment>: Look around the current room, at an exit/feature, or at a specific item/equipment.\n" +
                            "- GET <item|equipment>: Pick up an item or equipment from the room.\n" +
                            "- DROP <item|equipment>: Drop an item or equipment from your inventory.\n" +
                            "- USE <equipment> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item.\n" +
                            "- STATUS <inventory|player|item|equipment|map|score>: Check your current status/inventory, get information about a specific item/equipment, or display the map and your score.\n" +
                            "- HELP <topic>: Display this help information or get help on a specific command.\n" +
                            "- COMBINE <item1> and <item2>: Combine two items into a new item or equipment.\n" +
                            "- QUIT [save]: Exit the game. Include \'save\' to save your progress\n";
            default ->
                    "Welcome to the game! You can use the following commands:\n" +
                    "- MOVE <exit name>: Move to a different location as defined by an exit’s name.\n" +
                    "- LOOK <room|exit|features>|<item>|<equipment>: Look around the current room, at an exit/feature, or at a specific item/equipment.\n" +
                    "- GET <item|equipment>: Pick up an item or equipment from the room.\n" +
                    "- DROP <item|equipment>: Drop an item or equipment from your inventory.\n" +
                    "- USE <equipment> on|with <feature|item>: Use an item in your inventory on its own, or on a feature or item.\n" +
                    "- STATUS <inventory|player|item|equipment|map|score>: Check your current status/inventory, get information about a specific item/equipment, or display the map and your score.\n" +
                    "- HELP <topic>: Display this help information or get help on a specific command.\n" +
                    "- COMBINE <item1> and <item2>: Combine two items into a new item or equipment.\n" +
                    "- QUIT [save]: Exit the game. Include \'save\' to save your progress\n";
        };
    }

    @Override
    public String toString() {
        return "HELP command with value " + value;
    }

}
