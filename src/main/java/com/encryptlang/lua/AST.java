package com.encryptlang.lua;

/**
 *  ast
 */
public abstract class AST {

    public Token token;  // origin token unit

    public AST(){}

    public AST(Token token){
        this.token = token;
    }

    public AST(TokenType type){
        this.token = new Token(type,"");
    }


    @Override
    public String toString() {
        return token!= null ? token.toString() : "nil";
    }

    public abstract String toStringTree();

}