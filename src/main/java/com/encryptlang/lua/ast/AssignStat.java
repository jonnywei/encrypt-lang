package com.encryptlang.lua.ast;


public class AssignStat extends StatNode {

    public String variable;

    public ExprNode expression;


    public AssignStat(String variable, ExprNode expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Assign";
    }

    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(variable);
        buf.append(" ");
        buf.append(expression);
        buf.append(")");
        return buf.toString();
    }
}
