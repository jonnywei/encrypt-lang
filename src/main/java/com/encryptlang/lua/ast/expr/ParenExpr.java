package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

public class ParenExpr extends ExprNode {

    public  ExprNode expr;

    public ParenExpr(ExprNode expr) {
        this.expr = expr;
    }


    @Override
    public String toString() {
        return "ParenExpr";
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
