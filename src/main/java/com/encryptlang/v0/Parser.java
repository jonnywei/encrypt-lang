package com.encryptlang.v0;

import com.encryptlang.v0.form.ListForm;
import com.encryptlang.v0.node.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/6.
 */
public class Parser {


    List<ListForm> parse(List<Token> tokenList){

        List<ListForm> result = new ArrayList<>();

        int size = tokenList.size();
        for(int i=0; i < size; i++){
            Token t = tokenList.get(i);
            if(t instanceof Token.WordToken ){
                //匹配 define名字
//                matchDefineFormat();

            }
        }


        return result;
    }


    public ListForm matchDefineFormat(List<Token> tokenList, int idx){

        Token.WordToken t = (Token.WordToken ) tokenList.get(idx++);
        if(t.getName().equals("define")){
            Token var = tokenList.get(idx++);
            if(var instanceof Token.WordToken){ //匹配 变量
                Token assign = tokenList.get(idx++);
                if(assign instanceof Token.Assign){

                }
            }
        }
        return null;
    }

    public ListForm matchAssignFormat(){
        return null;
    }




    public ListForm matchExpression(List<Token> tokenList, int idx){


        return null;
    }

//
//    public ListForm matchFunctionFormat(List<Token> tokenList, int idx){
//        Token.WordToken fname = (Token.WordToken ) tokenList.get(idx++);
//        Token  next = tokenList.get(idx++);
//        if(next  instanceof  Token.OpenParenthensis){
//
//        }
//        Token.WordToken fname = (Token.WordToken ) tokenList.get(idx++);
//        Token.WordToken fname = (Token.WordToken ) tokenList.get(idx++);
//
//        return null;
//    }
//
//
//    public ListForm matchFunctionParamListFormat(List<Token> tokenList, int idx){
//
//        ListForm  param = matchExpression(tokenList, idx);
//
//
//        ListForm  param = matchExpression(tokenList, idx);
//        ListForm  param = matchExpression(tokenList, idx);
//
//
//        if(next  instanceof  Token.OpenParenthensis){
//
//        }
//        Token.WordToken fname = (Token.WordToken ) tokenList.get(idx++);
//        Token.WordToken fname = (Token.WordToken ) tokenList.get(idx++);
//
//        return null;
//    }
//
//    public ListForm matchPlusFormat(List<Token> tokenList, int idx){
//        Token.WordToken t = (Token.WordToken ) tokenList.get(idx++);
//        return null;
//    }

}
