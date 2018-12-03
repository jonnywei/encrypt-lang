package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;

import java.util.List;

public class TableConstructExpr extends ExprNode {

    public List<ExprNode> keyExpr;

    public List<ExprNode> valueExpr;

    public TableConstructExpr(List<ExprNode> keyExpr, List<ExprNode> valueExpr) {
        this.keyExpr = keyExpr;
        this.valueExpr = valueExpr;
    }

    @Override
    public String toString() {
        return "TableConstructExpr";
    }

    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        for(int i=0; keyExpr != null && i< keyExpr.size();  i++){
            buf.append(" ");
            buf.append(keyExpr.get(i).toStringTree());
            if(valueExpr.get(i)!= null){
                buf.append("=");
                buf.append(valueExpr.get(i).toStringTree());
            }
        }
        buf.append(" ");
        buf.append(")");
        return buf.toString();
    }
}
