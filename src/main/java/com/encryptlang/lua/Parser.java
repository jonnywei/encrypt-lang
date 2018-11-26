package com.encryptlang.lua;

public abstract class Parser {

    Lexer lexer;
    Token lookahead;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lookahead = this.lexer.nextToken();
    }


    public void match(TokenType tokenType){
        if(lookahead.type == tokenType){
            consume();
        }else {
            throw new Error("expect type "+ tokenType
            + ", found "+ lookahead);
        }
    }

    public void consume(){
        this.lookahead = this.lexer.nextToken();
    }

    public Token lookahead(){
        return this.lookahead;
    }

}
