package com.encryptlang.lua.ast.stat;


import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.StatNode;

import java.util.List;

public class AssignStat extends StatNode {

    public List<ExprNode> variables;

    public List<ExprNode> expressions;


    public AssignStat(List<ExprNode> variables, List<ExprNode> expressions) {
        this.variables = variables;
        this.expressions = expressions;
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
        for(int i=0; variables != null && i<variables.size();  i++){
            buf.append(" ");
            buf.append(variables.get(i).toStringTree());
        }
        for(int i=0; expressions != null && i<expressions.size();  i++){
            buf.append(" ");
            buf.append(expressions.get(i).toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }
}
