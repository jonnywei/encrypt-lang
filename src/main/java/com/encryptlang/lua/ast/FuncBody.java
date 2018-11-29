package com.encryptlang.lua.ast;

import com.encryptlang.lua.ast.expr.VarargExpr;

import java.util.List;

public class FuncBody {

    public List<String> params;

    public VarargExpr varargExpr;

    public Block block;

    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        for(int i=0; params != null && i<params.size();  i++){
            buf.append(" ");
            buf.append(params.get(i));
        }
        if(varargExpr != null){
            buf.append(" ");
            buf.append(varargExpr.toStringTree());
        }
        buf.append(" ");
        buf.append(block.toStringTree());
        return buf.toString();
    }

}
