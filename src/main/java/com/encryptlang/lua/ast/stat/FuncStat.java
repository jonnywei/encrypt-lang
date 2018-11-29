package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.FuncBody;
import com.encryptlang.lua.ast.StatNode;

import java.util.List;

public class FuncStat extends StatNode {

    public List<String> dotNames;

    public String colonName;

    public FuncBody funcBody;

    public FuncStat(String colonName, List<String> dotNames) {
        this.dotNames = dotNames;
        this.colonName = colonName;
    }

    @Override
    public String toString() {
        return "Func:" +
                "name='" + dotNames + '\'' ;
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
