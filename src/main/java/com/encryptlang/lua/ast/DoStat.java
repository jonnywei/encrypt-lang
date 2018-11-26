package com.encryptlang.lua.ast;


// "do" Block "end"

public class DoStat extends StatNode {

    public Block block;

    public DoStat(Block block) {
        this.block = block;
    }


    @Override
    public String toString() {
        return "DO";
    }

    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(block.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
