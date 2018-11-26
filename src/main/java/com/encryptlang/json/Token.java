package com.encryptlang.json;

public class Token {

    public int type;

    public String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + JsonLexer.tokenNames[type] +
                ", text='" + text + '\'' +
                '}';
    }
}
