package com.encryptlang;

import com.encryptlang.node.AssignNode;
import com.encryptlang.node.Token;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public class Reader {


    private  List<Token> read(PushbackReader pushbackReader) throws IOException{
        List<Token> nodeList = new ArrayList<>();
        int ich =pushbackReader.read();
        while (ich != -1){
            nodeList.add( readNode(pushbackReader));
             ich =pushbackReader.read();

        }
        return nodeList;
    }

    private Token readNode(PushbackReader pushbackReader) throws IOException {
        readWhitespace(pushbackReader);
        char ch = (char) pushbackReader.read();
       if( ch ==Token.ASSIGN){
            return new AssignNode();
        }else if(ch == Token.OPEN_PARENTHESIS){
           return  new Token.OpenParenthensis();
        }else if(ch == Token.CLOSE_PARENTHESIS){
           return  new Token.CloseParenthensis();
        }else if(ch == Token.PLUS){
           return  new Token.PlusToken();
        }else if(ch == Token.COMMAT){
           return new Token.Comma();
        }else if(Character.isAlphabetic(ch)){
            pushbackReader.unread(ch);
           return  readWordNode(pushbackReader);
        }else if(ch == Token.DOUBLE_QUOTE){
           return  readStringToken(pushbackReader);
       }else {
            pushbackReader.unread(ch);
       }
        return Token.END_TOKEN;
    }

    public List<Token>  read(String sourceCode) throws IOException{
        return  read(new PushbackReader((new StringReader(sourceCode))));
    }

    /**
     * process whitespace
     * @param reader
     * @throws IOException
     */
    private static void readWhitespace(PushbackReader reader) throws IOException {
        char ch = (char) reader.read();
        while (Character.isWhitespace(ch)){
            ch = (char) reader.read();
        }
        reader.unread(ch);

    }


    public static Token readWordNode(PushbackReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ic = reader.read();
        char ch = (char) ic;
        while (ic !=-1 && Character.isAlphabetic(ch)) {
            sb.append(ch);
            ch = (char) reader.read();
        }
        reader.unread(ch);
        return new Token.WordNode(sb.toString());
    }



    public static Token readStringToken(PushbackReader reader) throws IOException {

        StringBuilder sb = new StringBuilder();
        int ic = reader.read();
        char ch = (char) ic;
        while (ic !=-1 && ch != Token.DOUBLE_QUOTE) {
            sb.append(ch);
            ch = (char) reader.read();
        }
        if(ic == -1){
            reader.unread(ch);
            throw new IllegalArgumentException("string not end");
        }
        return new Token.StringToken(sb.toString());
    }


}
