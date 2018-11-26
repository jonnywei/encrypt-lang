package com.encryptlang.lua.ast;

import com.encryptlang.lua.AST;

import java.util.List;

public class Block extends AST {

    public List<StatNode> statements;


    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        for(StatNode statNode : statements){
            buf.append(" " +statNode.toStringTree());
        }
        return buf.toString();
    }

}
