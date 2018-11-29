package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.FuncBody;

public class FuncDefExpr extends ExprNode {

    public FuncBody funcBody;

    public FuncDefExpr() {
    }


    @Override
    public String toString() {
        return "FuncDef:" ;
    }

    @Override
    public String toStringTree() {

        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(funcBody.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
