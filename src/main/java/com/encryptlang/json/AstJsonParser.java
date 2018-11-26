package com.encryptlang.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstJsonParser extends JsonParser {

    private AST root;

    private AST currentNode;

    public AstJsonParser(JsonLexer lexer) {
        super(lexer);
    }

    public void json(){
        element();
    }


    public AST parse(){
        json();
        return root;
    }

    public void element(){
       super.element();
    }



    private AST newAST( ){
        AST ast = new AST();
        if(root == null) {
            root = ast;
        }else {
            currentNode.addChildren(ast);
        }

        ast.parent = currentNode;

        AST _save = currentNode;
        currentNode = ast;
        ast.token = lookahead;
        return _save;
    }
    private void restoreAST(AST old){
        currentNode = old;
    }

    public void object(){

        AST old = newAST();
        match(JsonLexer.LBRACE);
        if(lookahead.type == JsonLexer.RBRACE){
            match(JsonLexer.RBRACE);
        }else {
            members();
            match(JsonLexer.RBRACE);
        }

        restoreAST(old);

    }
    public void members(){

        member();
        if(lookahead.type == JsonLexer.COMMA){
            match(JsonLexer.COMMA);
            members();
        }
    }
    public void member(){
        AST old = newAST();
        currentNode.name = lookahead.text;
        match(JsonLexer.STRING);
        match(JsonLexer.COLON);
        element();
        restoreAST(old);
    }

    public void array(){
        AST old = newAST();
        match(JsonLexer.LBRACK);
        if(lookahead.type == JsonLexer.RBRACK){
            match(JsonLexer.RBRACK);
        }else {
            elements();
            match(JsonLexer.RBRACK);
        }
        restoreAST(old);
    }

    public void elements(){
        element();
        if(lookahead.type == JsonLexer.COMMA){
            match(JsonLexer.COMMA);
            elements();
        }
    }


    public void number(){
        AST old = newAST();
        match(JsonLexer.NUMBER);
        restoreAST(old);
    }
    public void id(){
        AST old = newAST();
        super.id();
        restoreAST(old);
    }
    public void string(){
        AST old = newAST();
        match(JsonLexer.STRING);
        restoreAST(old);
    }


    private AST to(Token token){
        AST AST = new AST();
        AST.token = token;
        return AST;
    }


    public static class AST {

        public List<AST> children = new ArrayList<>();

        public AST parent;

        public Object object;

        private String name;

        private Token token;

        public Object eval(){

            if(token.type == JsonLexer.LBRACK){
                List array = new ArrayList();
                object = array;
                for(AST c : children){
                    array.add(c.eval());
                }
            }else  if(token.type == JsonLexer.LBRACE){
                Map map = new HashMap<>();
                object = map;
                for(AST c :children){
                    map.put(c.eval(), c.children.get(0).eval());
                }
            }else  if(token.type == JsonLexer.STRING){
                object = new String(token.text);
            }else  if(token.type == JsonLexer.ID){
                object = new String(token.text);
            }else  if(token.type == JsonLexer.NUMBER){
                object = new String(token.text);
            }
            return object;
        }

        public List<AST> getChildren() {
            return children;
        }
        public void addChildren(AST children) {
            this.children.add(children);
        }

        @Override
        public String toString() {

            return name != null ? name : "'"+token.text+"'";
        }

         public String toStringTree() {
            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString() );
             for(AST child : this.children){
                 buf.append(" "+child.toStringTree());
             }
             buf.append(")");
            return  buf.toString();
        }
    }
}
