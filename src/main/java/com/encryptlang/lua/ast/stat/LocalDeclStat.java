package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.FuncBody;
import com.encryptlang.lua.ast.StatNode;

import java.util.List;

public class LocalDeclStat extends StatNode {
    public List<String> names;
    public List<ExprNode> exprs;


    public LocalDeclStat(List<String> names, List<ExprNode> exprs) {
        this.names = names;
        this.exprs = exprs;
    }

    @Override
    public String toString() {
        return "LocalDec:"  ;
    }

    @Override
    public String toStringTree() {

        StringBuilder buf = new StringBuilder();
        buf.append("(");
        
        buf.append(this.toString());
        buf.append(" ");
        for(int i=0; names != null && i<names.size();  i++){
            buf.append(" ");
            buf.append(names.get(i));
        }
        for(int i=0; exprs != null && i<exprs.size();  i++){
            buf.append(" ");
            buf.append(exprs.get(i).toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }
}
