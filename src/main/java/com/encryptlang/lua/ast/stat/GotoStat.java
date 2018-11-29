package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.StatNode;

public class GotoStat extends StatNode {
    private String name;

    public GotoStat(String name) {
        this.name = name;
    }

    @Override
    public String toStringTree() {
        return "goto "+ name;
    }
}
