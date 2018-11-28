package com.encryptlang.lua.ast;

import com.encryptlang.lua.AST;

import java.util.List;

public class Block extends AST {

    public List<StatNode> statements;


    @Override
    public String toString() {
        return "Block";
    }

    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        for(StatNode statNode : statements){
            buf.append(" " +statNode.toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }

}
