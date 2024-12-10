package org.uob.a2.parser;

import java.util.ArrayList;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {
    ArrayList<Token> tokensList;
    public Tokeniser() {
    }
    public String sanitise(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", " ");
    }
    public void tokenise(String s) {
        tokensList = new ArrayList<Token>();
        String[] tokens = sanitise(s).split(" ");
        for (String token : tokens) {
            switch (token) {
                case "move":
                    tokensList.add(new Token(TokenType.MOVE));
                    break;
                case "get":
                    tokensList.add(new Token(TokenType.GET));
                    break;
                case "drop":
                    tokensList.add(new Token(TokenType.DROP));
                    break;
                case "use":
                    tokensList.add(new Token(TokenType.USE));
                    break;
                case "combine":
                    tokensList.add(new Token(TokenType.COMBINE));
                    break;
                case "look":
                    tokensList.add(new Token(TokenType.LOOK));
                    break;
                case "help":
                    tokensList.add(new Token(TokenType.HELP));
                    break;
                case "quit":
                    tokensList.add(new Token(TokenType.QUIT));
                    break;
                case "status":
                    tokensList.add(new Token(TokenType.STATUS));
                    break;
                case "on", "with", "and":
                    tokensList.add(new Token(TokenType.PREPOSITION, token));
                    break;
                default:
                    tokensList.add(new Token(TokenType.VAR, token));
                    break;
            }
        }
        tokensList.add(new Token(TokenType.EOL));
    }
    public ArrayList<Token> getTokens() {
        return tokensList;
    }

}
