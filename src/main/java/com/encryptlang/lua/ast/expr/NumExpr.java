package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

public class NumExpr extends ExprNode {
    private String str;

    public NumExpr(String str) {
        this.str = str;
    }

    @Override
    public String toStringTree() {
        return '\''+str+'\'';
    }
}
