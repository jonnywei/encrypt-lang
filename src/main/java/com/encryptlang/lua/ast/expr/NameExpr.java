package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

public class NameExpr extends ExprNode {

    public  String name;

    public NameExpr(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameExpr";
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
