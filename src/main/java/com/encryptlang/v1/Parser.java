package com.encryptlang.v1;

import com.encryptlang.v1.lex.Lexer;
import com.encryptlang.v1.node.*;

import java.io.IOException;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class Parser {


    private final Lexer lexer;


    private Token  currentToken;

    private Token lookaheadToken;

    private boolean lookaheadConsumed;




    public Parser(Lexer lexer) {
        this.lexer = lexer;
        lookaheadConsumed = true;
    }


    public  Token nextToken () throws IOException {
        if(lookaheadConsumed){
            Token token = lexer.read();
            while ( token.type == Token.Type.Space  || token.type == Token.Type.NewLine){
                token = lexer.read();
            }
            currentToken = token;
            return currentToken;
        }{
            currentToken = lookaheadToken;
            lookaheadConsumed = true;
            return  currentToken;
        }
    }

    public  Token lookahead() throws IOException {
        if(lookaheadConsumed){
            Token token = lexer.read();
            while ( token.type == Token.Type.Space  || token.type == Token.Type.NewLine){
                token = lexer.read();
            }
            lookaheadToken = token;
            lookaheadConsumed = false;
            return token;
        }{
            return  lookaheadToken;
        }
    }

    public ExpressionBlockNode parse() throws IOException {
        ExpressionBlockNode root = new ExpressionBlockNode();
        parseExpression(root);
        return root;
    }


    private void parseExpression(ExpressionBlockNode node) throws IOException {

        while ( lookahead().type != Token.Type.EndSymbol){
            node.add(parseExpression());
        }


    }

    private Node parseExpression( )  throws IOException{
        try {
            Token token = lookahead();
            switch (token.type){
                case Keyword:
                    Token var = nextToken();
                    Token id1 = nextToken();
                    token = lookahead();
                    if( token.type == Token.Type.Sign && token.value.equals("=")){
                        nextToken();
                        Node expression1 = parseExpression();
                        return new DefineNode(id1.value, expression1);
                    }else {
                        throw new IllegalArgumentException("var define error");
                    }
                case Identifier:
                    Token id = nextToken();
                    token = lookahead();
                    if( token.type == Token.Type.Sign && token.value .equals("=")){
                        Token assign = nextToken();
                        Node expression = parseExpression();
                        return new AssignNode(id.value,expression);
                    }else  if( token.type == Token.Type.Sign && token.value .equals( "(")){
                        return parseFunctionCallExpression();
                    }else  if( token.type == Token.Type.Sign && token.value .equals( "+")){
                        return parseConcateExpression();
                    } else {
                         return new IdentityNode(id.value);
                    }
                case String :
                    Token str = nextToken();
                    token = lookahead();
                    if( token.type == Token.Type.Sign && token.value .equals("+")){
                        return parseConcateExpression();
                    }else {
                        return new StringNode(str.value);
                    }
                 default:
                     nextToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Node parseFunctionCallExpression ()  throws IOException{
        String fuctionName = this.currentToken.value;
        Token beginSign = nextToken();
        ListNode paramNode =  parseParamExpression();
        Token endSign = nextToken();
        return new FunctionCallNode(fuctionName, paramNode);
    }


    private Node parseConcateExpression() throws IOException{
        Token curent = currentToken;
        Token plus = nextToken();
        Node expression = parseExpression();
         Node a = new AddNode(curent,expression);
        Token lookahead = lookahead();
//        if(lookahead.type == Token.Type.Sign && lookahead.value.equals("+")){
//            return new AddNode(a, parseConcateExpression());
//        }
        return a ;
    }




    private ListNode parseParamExpression() throws IOException{

        ListNode paramNode = new ListNode();
        parseParamExpression(paramNode);
        return paramNode;

    }


    private void parseParamExpression(   ListNode paramNode ) throws IOException{

        Token next = lookahead();
        if(next.type == Token.Type.Sign && next.value.equals(")")){
              paramNode.addNode(Node.EMPTY);
              return;
        }
        Node expression =  parseExpression();
        paramNode.addNode(expression);
         next = lookahead();
        if(next.type == Token.Type.Sign && next.value.equals(",")){
            nextToken();
            parseParamExpression(paramNode);
        }
        if(next.type == Token.Type.Sign && next.value.equals(")")){
            paramNode.addNode(Node.EMPTY);
            return;
        }

    }


}
