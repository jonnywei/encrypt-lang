package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.TokenType;
import com.encryptlang.lua.ast.ExprNode;

public class UnopExpr extends ExprNode {

    private TokenType op;

    private ExprNode expr;


    public UnopExpr(TokenType op, ExprNode expr) {
        this.op = op;
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "Unop";
    }

    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString()+":");
        buf.append(op);
        buf.append(" ");
        buf.append(expr.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
