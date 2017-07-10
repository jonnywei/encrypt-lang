package com.encryptlang.v1.lex;

import com.encryptlang.v1.Token;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class Lexer {
    private static enum State {
        Normal,
        Identifier,
        Sign,
        String,
        Space;
    }

    private State state;

    private final LinkedList<Token> tokenBuffer = new LinkedList<>();

    private StringBuilder readBuffer = null;

    private Boolean transferredMeaningSign = false;

    private final  Reader reader;


    private Token endToken = null;

    public Lexer(Reader reader){
        this.reader = reader;
        this.state = State.Normal;

    }


    public  Token read() throws IOException {
        if(endToken != null){
            return  endToken;
        }

        while (tokenBuffer.isEmpty()) {
            int read = reader.read();

            char c = (read == -1) ? '\0' : (char) read;

            while (! readChar(c)) {}

        }

        Token token = tokenBuffer.removeLast();

        if(token.type == Token.Type.EndSymbol){
            endToken = token;
        }
        return token;
    }


    private  void createToken(Token.Type type){
        Token token = new Token(type, readBuffer.toString());
        tokenBuffer.add(token);
        readBuffer = null;
    }

    private void refreshBuffer(char c){
        readBuffer = new StringBuilder();
        readBuffer.append(c);
    }

    private boolean readChar (char c ){
        boolean moveCursor = true;

        Token.Type createType = null;

        if(state == State.Normal){
            if(isIdentifierChar(c)){
                state = State.Identifier;
            }else  if (c =='+' || c =='=' || c==')' || c =='(' || c==','){

                state = State.Sign;

            } else  if (c == '"' || c == '\''){
                state = State.String;
                transferredMeaningSign = false;

            } else  if (c == '\n'){
                createType = Token.Type.NewLine;

            }else  if (include(Space,c)){
                state = State.Space;
            } else  if (c =='\0'){
                createType = Token.Type.EndSymbol;
            }else  {
                throw new IllegalArgumentException( c +" illegal char");
            }
            refreshBuffer(c);
        } else  if(state == State.Identifier){
            if(isIdentifierChar(c)){
                readBuffer.append(c);
            }else {
                createType = Token.Type.Identifier;
                state = State.Normal;
                moveCursor = false;
            }
        }else  if (state == State.Sign){

            createType = Token.Type.Sign;
            state = State.Normal;
            moveCursor = false;

        }else  if(state == State.Space){
            if(include(Space, c)){
                readBuffer.append(c);
            }else {
                createType = Token.Type.Space;
                state = State.Normal;
                moveCursor = false;
            }


        }else  if (state == State.String){
            if(c == '\n' ||  c == '\0'){
                throw new IllegalArgumentException("string not start new line or end");
            }else  if(transferredMeaningSign){
                readBuffer.append(c);
            }else if (c == '\\'){
                transferredMeaningSign = true;
            }else {
                readBuffer.append(c);

                char first = readBuffer.charAt(0);

                if(c == first){ //结束
                    createType = Token.Type.String;
                    state = State.Normal;
                }

            }


        }else {
            throw  new IllegalStateException("Lex state error");
        }

        if(createType != null){
            createToken(createType);
        }
        return moveCursor;
    }

    private boolean isIdentifierChar(char c){

        return  (c >= 'a' &&  c <= 'z') | (c >= 'A' &&  c <= 'Z' )
                | (c >= '0' & c <= '9') | c == '.';
    }

    private static final char[] Space = new char[] {' ', '\t'};

    private boolean include(char[] range, char c){
        boolean include = false;

        for(char m : range){
            if(m == c){
                include = true;
                break;
            }
        }
        return include;
    }

}
