package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.TokenType;
import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.FuncArgs;

public class PrefixExpr2 extends ExprNode {


    @Override
    public String toStringTree() {
        return null;
    }


    public static class NamePrefixExpr extends PrefixExpr2 {

        public  String name;

        public NamePrefixExpr(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "NamePrefixExpr";
        }

        @Override
        public String toStringTree() {

            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append(name);
            buf.append(")");
            return buf.toString();
        }
    }


    public static class ParenPrefixExpr extends PrefixExpr2 {

        public  ExprNode expr;

        public ParenPrefixExpr(ExprNode expr) {
            this.expr = expr;
        }


        @Override
        public String toString() {
            return "ParenPrefixExpr";
        }

        @Override
        public String toStringTree() {
            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append("'('");
            buf.append(expr.toStringTree());
            buf.append("')'");
            buf.append(" ");
            buf.append(")");
            return buf.toString();
        }
    }



    public static class PrefixSuffixExpr extends PrefixExpr2 {

        public TokenType tokenType;

        public  String name;

        public  ExprNode exprNode;

        public FuncArgs funcArgs;

        public  PrefixExpr2 expr;



        public PrefixSuffixExpr(TokenType tokenType, String name, FuncArgs funcArgs,PrefixExpr2 expr) {
            this.tokenType = tokenType;
            this.name = name;
            this.funcArgs = funcArgs;
            this.expr = expr;
        }

        public PrefixSuffixExpr(TokenType tokenType , FuncArgs funcArgs, PrefixExpr2 expr) {
            this.tokenType = tokenType;
            this.expr = expr;
            this.funcArgs = funcArgs;
        }

        public PrefixSuffixExpr(TokenType tokenType, ExprNode exprNode, PrefixExpr2 expr) {
            this.tokenType = tokenType;
            this.exprNode = exprNode;
            this.expr = expr;
        }

        public PrefixSuffixExpr(TokenType tokenType, String name, PrefixExpr2 expr) {
            this.tokenType = tokenType;
            this.name = name;
            this.expr = expr;
        }


        @Override
        public String toString() {
            return "PrefixSuffixExpr";
        }

        @Override
        public String toStringTree() {

            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append(this.tokenType);
            buf.append(" ");
            if(name != null){
                buf.append(name);
            }else if(exprNode != null){
                buf.append(exprNode.toStringTree());
            }
            if(expr != null){
                buf.append(" ");
                buf.append(expr.toStringTree());
            }
            buf.append(")");
            return buf.toString();
        }
    }
}
