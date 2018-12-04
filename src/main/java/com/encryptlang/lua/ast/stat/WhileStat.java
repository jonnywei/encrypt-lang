package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.Block;
import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.StatNode;

public class WhileStat extends StatNode {

    public ExprNode expr;

    public Block block;

    public WhileStat(ExprNode expr,Block block) {
        this.expr = expr;
        this.block = block;
    }
    @Override
    public String toString() {
        return "WHILE";
    }
    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(expr.toStringTree());
        buf.append(" ");
        buf.append(block.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
