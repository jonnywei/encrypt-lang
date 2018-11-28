package com.encryptlang.json;

public abstract class LLkParser {

    Lexer lexer;
    Token[] lookahead;
    final  int k;
    int p = 0;

    public LLkParser(Lexer lexer, int k) {
        this.lexer = lexer;
        this.k = k;
        for(int i =0; i<k; i++){
            consume();
        }
    }

    public Token lookaheadToken(int i){
        return lookahead[(p+i-1)%k];
    }
    public int lookaheadTokenType(int i){
        return lookaheadToken(i).type;
    }
    public void match(int tokenType){
        if(lookaheadTokenType(1) == tokenType){
            consume();
        }else {
            throw new Error("expect type "+ lexer.getTokenName(tokenType)
            + ", found "+ lookaheadTokenType(1));
        }
    }

    public void consume(){
        this.lookahead[p] = this.lexer.nextToken();
        p = (p+1)%k;
    }


}
