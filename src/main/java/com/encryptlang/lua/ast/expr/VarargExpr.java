package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

public class VarargExpr extends ExprNode {
    private String str;

    public VarargExpr() {
        this.str = "...";
    }

    public VarargExpr(String str) {
        this.str = str;
    }

    @Override
    public String toStringTree() {
        return '\''+str+'\'';
    }
}
