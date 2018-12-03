package com.encryptlang.lua.ast.expr;

import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.FuncArgs;


public class FuncCallExpr extends ExprNode {

    public  ExprNode prefix;

    public String name;

    public FuncArgs args;

    public FuncCallExpr(ExprNode prefix, String name, FuncArgs args) {
        this.prefix = prefix;
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return "Func:" ;
    }

    @Override
    public String toStringTree() {

        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(prefix.toStringTree());
        if(name != null){
            buf.append(":"+name);
        }
        buf.append(args.toStringTree());
        buf.append(")");
        return buf.toString();
    }
}
