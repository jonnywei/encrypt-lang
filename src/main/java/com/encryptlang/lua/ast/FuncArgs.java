package com.encryptlang.lua.ast;

import com.encryptlang.lua.ast.expr.TableConstructExpr;

import java.util.List;

public class FuncArgs {

    public List<ExprNode> args;

    private String strArg;

    private TableConstructExpr table;


    public FuncArgs(List<ExprNode> args) {
        this.args = args;
    }

    public FuncArgs(String strArg) {
        this.strArg = strArg;
    }

    public FuncArgs(TableConstructExpr table) {
        this.table = table;
    }

    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        for(int i=0; args != null && i<args.size();  i++){
            buf.append(" ");
            buf.append(args.get(i).toStringTree());
        }
        if(strArg != null){
            buf.append(" ");
            buf.append(strArg);
        }
        if(table != null){
            buf.append(" ");
            buf.append(table.toStringTree());
        }
        return buf.toString();
    }

}
