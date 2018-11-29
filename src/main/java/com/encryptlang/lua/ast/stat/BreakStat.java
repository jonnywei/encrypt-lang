package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.StatNode;

public class BreakStat extends StatNode {
    @Override
    public String toStringTree() {
        return "break";
    }
}
