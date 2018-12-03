package com.encryptlang.lua.ast.stat;

import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.StatNode;

import java.util.List;

public class ReturnStat  extends StatNode {

    List<ExprNode> exprs;


    public ReturnStat(List<ExprNode> exprs) {
        this.exprs = exprs;
    }

    @Override
    public String toString() {
        return "ReturnStat";
    }

    @Override
    public String toStringTree(){
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        for(int i=0; exprs != null && i<exprs.size();  i++){
            buf.append(" ");
            buf.append(exprs.get(i).toStringTree());
        }

        buf.append(")");
        return buf.toString();
    }
}
