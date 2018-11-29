package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

public class NilExp extends ExprNode {

    @Override
    public String toStringTree() {
        return "nil";
    }
}
