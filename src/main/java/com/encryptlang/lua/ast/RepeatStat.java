package com.encryptlang.lua.ast;

public class RepeatStat extends StatNode {

    public ExprNode expr;

    public Block block;

    public RepeatStat(ExprNode expr, Block block) {
        this.expr = expr;
        this.block = block;
    }
    @Override
    public String toString() {
        return "REPEAT";
    }
    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(block.toStringTree());
        buf.append(" ");
        buf.append(expr.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
