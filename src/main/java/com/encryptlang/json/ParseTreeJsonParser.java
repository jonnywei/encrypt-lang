package com.encryptlang.json;

import java.util.ArrayList;
import java.util.List;

public class ParseTreeJsonParser extends JsonParser {

    private ParseTree root;

    private ParseTree currentNode;

    public ParseTreeJsonParser(JsonLexer lexer) {
        super(lexer);
    }


    public ParseTree parse(){
        json();
        return root;
    }


    private ParseTree newNode(String rule){
        ParseTree parseTree = new ParseTree(rule);
        if(root == null) {
            root = parseTree;
        }else {
            currentNode.addChildren(parseTree);
        }
        ParseTree _save = currentNode;
        currentNode = parseTree;
        return _save;
    }

    private void restoreNode(ParseTree old){
        currentNode = old;
    }

    public void json(){

        ParseTree _save = newNode("json");
        super.json();
        restoreNode(_save);

    }

    public void element(){
        ParseTree _save = newNode("element");
        super.element();
        restoreNode(_save);
    }

    public void object(){

        ParseTree _save = newNode("object");
        super.object();
        restoreNode(_save);
    }
    public void members(){
        ParseTree _save = newNode("members");
        super.members();
        restoreNode(_save);
    }
    public void member(){
        ParseTree _save = newNode("member");
        super.member();
        restoreNode(_save);
    }

    public void array(){
        ParseTree _save = newNode("array");
        super.array();
        restoreNode(_save);
    }

    public void elements(){
        ParseTree _save = newNode("elements");
        super.elements();
        restoreNode(_save);
    }


    public void number(){
        ParseTree _save = newNode("number");
        super.number();
        restoreNode(_save);
    }
    public void id(){
        ParseTree _save = newNode("id");
        super.id();
        restoreNode(_save);

    }
    public void string(){
        ParseTree _save = newNode("string");
        super.string();
        restoreNode(_save);
    }

    public void match(int x){
        currentNode.addChildren(to(lookahead));
        super.match(x);
    }

    private ParseTree to(Token token){
        ParseTree parseTree = new ParseTree(null);
        parseTree.token = token;
        return parseTree;
    }


    public static class ParseTree {

        public List<ParseTree> children = new ArrayList<>();

        private Token token;

        public String rule;

        public ParseTree(String rule) {
            this.rule = rule;
        }

        public List<ParseTree> getChildren() {
            return children;
        }

        public void addChildren(ParseTree children) {
            this.children.add(children);
        }

        public Token getToken() {
            return token;
        }

        public void setToken(Token token) {
            this.token = token;
        }

        @Override
        public String toString() {

            return rule != null ? rule : "'"+token.text+"'";
        }

         public String toStringTree() {

            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString() );
             for(ParseTree child : this.children){
                 buf.append(" "+child.toStringTree());
             }
             buf.append(")");
            return  buf.toString();
        }
    }
}
