package com.encryptlang.lua.ast;


public class FuncCallStat extends StatNode {

    public String name;

    public FuncArgs args;


    public FuncCallStat(String name, FuncArgs args) {
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
        buf.append(" ");
        buf.append(args.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
