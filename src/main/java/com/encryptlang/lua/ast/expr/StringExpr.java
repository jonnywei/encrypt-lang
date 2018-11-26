package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

public class StringExpr extends ExprNode {
    private String str;

    public StringExpr(String str) {
        this.str = str;
    }

    @Override
    public String toStringTree() {
        return '\''+str+'\'';
    }
}
