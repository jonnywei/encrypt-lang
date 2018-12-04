package com.encryptlang.lua.ast.stat;


// "do" Block "end"

import com.encryptlang.lua.ast.Block;
import com.encryptlang.lua.ast.StatNode;

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
