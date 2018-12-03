package com.encryptlang.lua.ast;

import com.encryptlang.lua.AST;
import com.encryptlang.lua.ast.stat.ReturnStat;

import java.util.List;

public class Block extends AST {

    public List<StatNode> statements;

    public ReturnStat returnStat;


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
        if(returnStat != null){
            buf.append(" "+ returnStat.toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }

}
