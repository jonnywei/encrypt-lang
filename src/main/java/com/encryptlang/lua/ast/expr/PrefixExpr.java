package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.TokenType;
import com.encryptlang.lua.ast.ExprNode;

public class PrefixExpr extends ExprNode {


    @Override
    public String toStringTree() {
        return null;
    }


    public static class PrefixSuffixExpr extends com.encryptlang.lua.ast.expr.PrefixExpr {

        public TokenType tokenType;

        public  String name;

        public  ExprNode exprNode;

        public  ExprNode lhs;


        public PrefixSuffixExpr(TokenType tokenType, ExprNode exprNode, ExprNode lhs) {
            this.tokenType = tokenType;
            this.exprNode = exprNode;
            this.lhs = lhs;
        }

        public PrefixSuffixExpr(TokenType tokenType, String name, ExprNode lhs) {
            this.tokenType = tokenType;
            this.name = name;
            this.lhs = lhs;
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

            if(lhs != null){
                buf.append(" ");
                buf.append(lhs.toStringTree());
            }

            if(name != null){
                buf.append(name);
            }else if(exprNode != null){
                buf.append(exprNode.toStringTree());
            }

            buf.append(")");
            return buf.toString();
        }
    }


    public static class PrefixExpr2 extends com.encryptlang.lua.ast.expr.PrefixExpr {

        public  ExprNode lhs;

        public  ExprNode rhs;


        public PrefixExpr2(ExprNode lhs, ExprNode rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }

        @Override
        public String toString() {
            return "PrefixExpr";
        }

        @Override
        public String toStringTree() {
            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append(lhs.toStringTree());
            buf.append(" ");
            buf.append(rhs.toStringTree());
            buf.append(")");
            return buf.toString();
        }
    }
}
