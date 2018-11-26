package com.encryptlang.lua.ast;

public class EmptyStat extends StatNode {
    @Override
    public String toStringTree() {
        return ";";
    }
}
