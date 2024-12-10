package org.uob.a2.parser;

import org.uob.a2.commands.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 *
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {
    public Parser() {
    }

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.size() == 0) {
            throw new CommandErrorException("No command entered.");
        }
        Token firstToken = tokens.get(0);
        switch (firstToken.getTokenType()) {
            case MOVE -> {
                if (tokens.size() != 3) {
                    throw new CommandErrorException("Invalid MOVE command format. Expected: MOVE <direction>");
                }
                return new Move(tokens.get(1).getValue());
            }
            case GET -> {
                if (tokens.size() != 3) {
                    throw new CommandErrorException("Invalid GET command format. Expected: GET <item>");
                }
                return new Get(tokens.get(1).getValue());
            }
            case DROP -> {
                if (tokens.size() != 3) {
                    throw new CommandErrorException("Invalid DROP command format. Expected: DROP <item>");
                }
                return new Drop(tokens.get(1).getValue());
            }
            case USE -> {
                if (tokens.size() == 5){
                    if (tokens.get(2).getTokenType() != TokenType.PREPOSITION){
                        throw new CommandErrorException("Invalid USE command format. Expected: USE <item> ON <object>");
                    }
                    else {
                        return new Use(tokens.get(1).getValue(), tokens.get(3).getValue());
                    }
                } else if (tokens.size() == 3) {
                    return new Use(tokens.get(1).getValue(), "room");
                } else{
                    throw new CommandErrorException("Invalid USE command format. Expected: USE <item> ON <object>");
                }

            }
            case LOOK -> {
                if (tokens.size() < 3) {
                    throw new CommandErrorException("Invalid LOOK command format. Expected: LOOK <object>");
                }
                return new Look(tokens.stream()
                      .filter(token -> token.getTokenType() == TokenType.VAR)
                      .map(Token::getValue)
                      .collect(Collectors.joining(" ")));
            }
            case HELP -> {
                if (tokens.get(1).getTokenType() == TokenType.EOL) {
                    return new Help(null);
                }
                return new Help(tokens.get(1).getTokenType().name());
            }
            case QUIT -> {
                return new Quit();
            }
            case COMBINE -> {
                if (tokens.size() != 5 || (tokens.get(2).getTokenType() != TokenType.PREPOSITION)) {
                    throw new CommandErrorException("Invalid COMBINE command format. Expected: COMBINE <item> WITH <object>");
                }
                return new Combine(tokens.get(1).getValue(), tokens.get(3).getValue());
            }
            case STATUS -> {
                if (tokens.get(1).getTokenType() == TokenType.EOL) {
                    return new Status(null);
                } else {
                    return new Status(tokens.stream()
                            .filter(token -> token.getTokenType() == TokenType.VAR)
                            .map(Token::getValue)
                            .collect(Collectors.joining(" ")));
                }
            }
            default -> throw new CommandErrorException("Unrecognized command: " + firstToken.getValue());
        }

    }

}
