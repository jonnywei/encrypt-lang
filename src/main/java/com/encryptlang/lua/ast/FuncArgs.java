package com.encryptlang.lua.ast;

import java.util.List;

public class FuncArgs {

    public List<ExprNode> args;


    public FuncArgs(List<ExprNode> args) {
        this.args = args;
    }

    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        for(int i=0; args != null && i<args.size();  i++){
            buf.append(" ");
            buf.append(args.get(i).toStringTree());
        }
        return buf.toString();
    }

}
