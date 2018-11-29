package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.FuncBody;
import com.encryptlang.lua.ast.StatNode;

public class LocalFuncStat extends StatNode {
    public String name;
    public FuncBody funcBody;


    public LocalFuncStat(String name, FuncBody funcBody) {
        this.name = name;
        this.funcBody = funcBody;
    }

    @Override
    public String toString() {
        return "LocalFunc:" +
                "name='" + name + '\'' ;
    }

    @Override
    public String toStringTree() {

        StringBuilder buf = new StringBuilder();
        buf.append("(");
        
        buf.append(this.toString());

        buf.append(funcBody.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
