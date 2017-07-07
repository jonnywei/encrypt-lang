package com.encryptlang.v1;

import java.util.HashSet;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class Token {
    private static final HashSet<String> keywordsSet = new HashSet<>();

    static {
        keywordsSet.add("def");
        keywordsSet.add("var");

    }

    public static enum Type {
        Keyword,
        Number,
        Identifier,
        Sign,
        String,
        Space,
        NewLine,
        EndSymbol;
    }

    public final Type type;
    public  final String value;

    public Token(Type type, String value) {
        if(type == Type.Identifier){
            char firstChar = value.charAt(0);
            if(firstChar >='0' && firstChar <='9'){
                type = Type.Number;
            }else if (keywordsSet.contains(value)){
                type = Type.Keyword;
            }
        }else  if(type == Type.String){
            value = value.substring(1, value.length() -1);
        }else  if(type == Type.EndSymbol){
            value = null;
        }
        this.type = type;
        this.value = value;
    }

    @Override public String toString() {
        return "Token{" + "type=" + type+ ", value='" + value + '\'' + '}';
    }
}
