package com.encryptlang.json;

public abstract class Parser {

    Lexer lexer;
    Token lookahead;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lookahead = this.lexer.nextToken();
    }


    public void match(int tokenType){
        if(lookahead.type == tokenType){
            consume();
        }else {
            throw new Error("expect type "+ lexer.getTokenName(tokenType)
            + ", found "+ lookahead);
        }
    }

    public void consume(){
        this.lookahead = this.lexer.nextToken();
    }


}
