package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.TokenType;
import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.FuncArgs;

public class PrefixExpr extends ExprNode {


    @Override
    public String toStringTree() {
        return null;
    }



    public static class NamePrefixExpr extends PrefixExpr {

        public  String name;

        public  PrefixSuffixExpr suffix;


        public NamePrefixExpr(String name, PrefixSuffixExpr suffix) {
            this.name = name;
            this.suffix = suffix;
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
            buf.append(" ");
            if(suffix != null){
                buf.append(suffix.toStringTree());
            }
            buf.append(")");
            return buf.toString();
        }
    }


    public static class ParenPrefixExpr extends PrefixExpr {

        public  ExprNode expr;

        public  PrefixSuffixExpr suffix;


        public ParenPrefixExpr(ExprNode expr, PrefixSuffixExpr suffix) {
            this.expr = expr;
            this.suffix = suffix;
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
            buf.append(expr.toStringTree());
            buf.append(" ");
            if(suffix != null){
                buf.append(suffix.toStringTree());
            }
            buf.append(")");
            return buf.toString();
        }
    }



    public static class PrefixSuffixExpr extends PrefixExpr {

        public TokenType tokenType;

        public  String name;

        public  ExprNode exprNode;

        public FuncArgs funcArgs;


        public  PrefixSuffixExpr expr;



        public PrefixSuffixExpr(TokenType tokenType, String name, FuncArgs funcArgs,PrefixSuffixExpr expr) {
            this.tokenType = tokenType;
            this.name = name;
            this.funcArgs = funcArgs;
            this.expr = expr;
        }

        public PrefixSuffixExpr(TokenType tokenType , FuncArgs funcArgs, PrefixSuffixExpr expr) {
            this.tokenType = tokenType;
            this.expr = expr;
            this.funcArgs = funcArgs;
        }

        public PrefixSuffixExpr(TokenType tokenType, ExprNode exprNode, PrefixSuffixExpr expr) {
            this.tokenType = tokenType;
            this.exprNode = exprNode;
            this.expr = expr;
        }

        public PrefixSuffixExpr(TokenType tokenType, String name, PrefixSuffixExpr expr) {
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
