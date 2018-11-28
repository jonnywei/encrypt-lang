package com.encryptlang.lua;

public abstract class Parser {

    private Lexer lexer;
    private Token lookahead;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
//        this.lookahead = this.lexer.nextToken();
    }

    /**
     * lookahead token
     * @param i
     * @return
     */
    public Token LT(int i){
        if(i != 1){
            throw  new IllegalArgumentException("only see one");
        }
        return lookahead;
    }

    /**
     * lookahead type
     * @param i
     * @return
     */
    public TokenType LA(int i){
        if(i != 1){
            throw  new IllegalArgumentException("only see one");
        }
        return LT(i).type;
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

}
