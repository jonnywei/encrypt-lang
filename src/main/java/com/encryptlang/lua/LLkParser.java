package com.encryptlang.lua;



public abstract class LLkParser extends Parser {
   Lexer lexer;
   Token[] lookahead;

   final  int k;
   int p = 0;

    public LLkParser(Lexer lexer, int k) {
        super(lexer);
        this.lexer = lexer;
        this.k = k;
        this.lookahead = new Token[this.k];
        for(int i =0; i<k; i++){
            consume();
        }
    }

    public Token LT(int i){
        return lookahead[(p+i-1)%k];
    }
    public TokenType LA(int i){
        return LT(i).type;
    }

    public void match(TokenType tokenType){
        if(LA(1) == tokenType){
            consume();
        }else {
            throw new Error("expect type "+ tokenType
                    + ", found "+ LT(1));
        }
    }

    public void consume(){
        this.lookahead[p] = this.lexer.nextToken();
        p = (p+1)%k;
    }


}
