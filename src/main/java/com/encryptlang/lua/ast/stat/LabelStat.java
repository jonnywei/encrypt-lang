package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.StatNode;

public class LabelStat extends StatNode {
    private String name;

    public LabelStat(String name) {
        this.name = name;
    }

    @Override
    public String toStringTree() {
        return "Label "+ name;
    }
}
