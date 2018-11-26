package com.encryptlang.json;

public class JsonParser extends Parser {
    public JsonParser(JsonLexer lexer) {
        super(lexer);
    }

    public void json(){
        element();
    }

    public void element(){
        if(lookahead.type == JsonLexer.LBRACE){
            object();
        }else if (lookahead.type == JsonLexer.LBRACK){
            array();
        }else if(lookahead.type == JsonLexer.STRING){
            string();
        }else if(lookahead.type == JsonLexer.NUMBER){
            number();
        }else if(lookahead.type == JsonLexer.TRUE ||
                lookahead.type ==  JsonLexer.FALSE ||
                lookahead.type ==  JsonLexer.NULL){
            id();
        }else {
            throw new Error("expect json object, found " + lookahead);
        }
    }

    public void object(){
        match(JsonLexer.LBRACE);
        if(lookahead.type == JsonLexer.RBRACE){
            match(JsonLexer.RBRACE);
        }else {
           members();
           match(JsonLexer.RBRACE);
        }
    }
    public void members(){
        member();
        if(lookahead.type == JsonLexer.COMMA){
            match(JsonLexer.COMMA);
            members();
        }
    }
    public void member(){
        match(JsonLexer.STRING);
        match(JsonLexer.COLON);
        element();
    }

    public void array(){
        match(JsonLexer.LBRACK);
        if(lookahead.type == JsonLexer.RBRACK){
            match(JsonLexer.RBRACK);
        }else {
            elements();
            match(JsonLexer.RBRACK);
        }
    }

    public void elements(){
        element();
        if(lookahead.type == JsonLexer.COMMA){
            match(JsonLexer.COMMA);
            elements();
        }
    }


    public void number(){
        match(JsonLexer.NUMBER);
    }
    public void id(){
        if(lookahead.text.equalsIgnoreCase("true")){
            match(JsonLexer.TRUE);
        } else if(lookahead.text.equalsIgnoreCase("false")){
            match(JsonLexer.FALSE);
        }else if(lookahead.text.equalsIgnoreCase("null")){
            match(JsonLexer.NULL);
        }else {
            throw new Error("not json keyword "+ lookahead);
        }

    }
    public void string(){
        match(JsonLexer.STRING);
    }

}
