package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.TokenType;
import com.encryptlang.lua.ast.ExprNode;

public class BinopExpr extends ExprNode {

    private TokenType op;

    private ExprNode leftExpr;

    private ExprNode rightExpr;

    public BinopExpr(TokenType op, ExprNode leftExpr, ExprNode rightExpr) {
        this.op = op;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    public String toString() {
        return "Binop";
    }

    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString()+":");
        buf.append(op);
        buf.append(" ");
        buf.append(leftExpr.toStringTree());
        buf.append(" ");
        buf.append(rightExpr.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
