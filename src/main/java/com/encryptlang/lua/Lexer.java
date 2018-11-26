package com.encryptlang.lua;

public abstract  class Lexer {

    public static final char EOF = (char) -1;

    String input;

    int p =0;

    char c;

    int line = 1;

    public Lexer(String input) {
        this.input = input;
        if(input.length() <=0){
            c = EOF;
        }else {
            c = input.charAt(p);
        }
    }


    public void consume(){
        p++;
        if(p >= input.length()) {
            c = EOF;
        }else {
            c = input.charAt(p);
        }
    }

    public char nextChar(int i){
        int n = p+i;
        if(n >= input.length()) {
            return  EOF;
        }else {
            return input.charAt(n);
        }
    }

    public void match(char x){
        if(c == x) consume();
        else {
            throw new Error("expect "+x+"; found "+ c);
        }
    }

    public abstract Token nextToken();


}
