package com.encryptlang.lua;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        KEYWORDS.put("nil",TokenType.NIL);
        KEYWORDS.put("function",TokenType.FUNCTION);
        KEYWORDS.put("local",TokenType.LOCAL);
        KEYWORDS.put("break",TokenType.BREAK);
        KEYWORDS.put("goto",TokenType.GOTO);
        KEYWORDS.put("return",TokenType.RETURN);
        KEYWORDS.put("and",TokenType.AND);
        KEYWORDS.put("or",TokenType.OR);
        KEYWORDS.put("not",TokenType.NOT);

    }
    public static Map<TokenType,Integer> UNARYOPS = new HashMap<>();

    public static Map<TokenType,Integer> BINOPS = new HashMap<>();
    public static Set<TokenType> BINOP_RIGHT_ASSOC = new HashSet<>();

    /**
     *      or                                         1
     *      and                                        2
     *      <     >     <=    >=    ~=    ==           3
     *      |                                          4
     *      ~
     *      &
     *      <<    >>
     *      ..
     *      +     -                                    9
     *      *     /     //    %                        10
     *      unary operators (not   #     -     ~)      11
     *      ^                                          12
     */
    static {

        BINOPS.put(TokenType.OR,1);
        BINOPS.put(TokenType.AND,2);
        BINOPS.put(TokenType.POWER,3);

        BINOPS.put(TokenType.ADD,9);
        BINOPS.put(TokenType.SUB,9);
        BINOPS.put(TokenType.MUL,10);
        BINOPS.put(TokenType.DIV,10);

        BINOPS.put(TokenType.POWER,12);

       //   not   #     -     ~
        UNARYOPS.put(TokenType.NOT,11);
        UNARYOPS.put(TokenType.LENGTH,11);
        UNARYOPS.put(TokenType.SUB,11);
        UNARYOPS.put(TokenType.BNOT,11);


    }

    static {
        BINOP_RIGHT_ASSOC.add(TokenType.POWER);
    }



    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }



    public static TokenType isUnary(TokenType type){
        if(UNARYOPS.containsKey(type) ){
            if(type == TokenType.SUB){
                return TokenType.UNMINUS;
            }
            if(type == TokenType.BXOR){
                return TokenType.BNOT;
            }
            return type;
        }
        return null;
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
