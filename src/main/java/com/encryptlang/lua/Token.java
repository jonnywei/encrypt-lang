package com.encryptlang.lua;

import java.util.HashMap;
import java.util.Map;

public class Token {

    public TokenType type;

    public String text;


    public static Map<String, TokenType> KEYWORDS = new HashMap<>();
    static {
        KEYWORDS.put("do",TokenType.DO);
        KEYWORDS.put("end",TokenType.END);
        KEYWORDS.put("while",TokenType.WHILE);
        KEYWORDS.put("true",TokenType.TRUE);
        KEYWORDS.put("false",TokenType.FALSE);
        KEYWORDS.put("if",TokenType.IF);
        KEYWORDS.put("then",TokenType.THEN);
        KEYWORDS.put("else",TokenType.ELSE);
        KEYWORDS.put("elseif",TokenType.ELSEIF);
        KEYWORDS.put("repeat",TokenType.REPEAT);
        KEYWORDS.put("until",TokenType.UNTIL);
        KEYWORDS.put("for",TokenType.FOR);
        KEYWORDS.put("in",TokenType.IN);

    }



    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public static Token getKeyWord(String text){
        TokenType tokenType =  KEYWORDS.get(text);
        if(tokenType != null){
            return new Token(tokenType, text);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type.name() +
                ", text='" + text + '\'' +
                '}';
    }
}
