package com.encryptlang.lua.ast;

import java.util.List;

public class FuncCallStat extends StatNode {

    public String name;

    public List<ExprNode> args;


    public FuncCallStat(String name, List<ExprNode> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return "Func:" +
                "name='" + name + '\'' ;
    }

    @Override
    public String toStringTree() {

        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        for(int i=0; i<args.size();  i++){
            buf.append(" ");
            buf.append(args.get(i).toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }
}