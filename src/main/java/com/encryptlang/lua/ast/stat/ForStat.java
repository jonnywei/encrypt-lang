package com.encryptlang.lua.ast.stat;


import com.encryptlang.lua.ast.Block;
import com.encryptlang.lua.ast.ExprNode;
import com.encryptlang.lua.ast.StatNode;

import java.util.List;

// for Name ‘=’ exp ‘,’ exp [‘,’ exp] do block end |
// for namelist in explist do block end |
//	namelist ::= Name {‘,’ Name}
// 	explist ::= exp {‘,’ exp}
public abstract  class ForStat extends StatNode {

    public Block block;

    @Override
    public String toString() {
        return "For";
    }

    public static class ForInitStat extends ForStat {

        public String name;
        public ExprNode initExpr;
        public ExprNode terminationExpr;
        public ExprNode incrementExpr;

        public ForInitStat(String name, ExprNode initExpr, ExprNode terminationExpr) {
            this.name = name;
            this.initExpr = initExpr;
            this.terminationExpr = terminationExpr;
        }

        @Override
        public String toString() {
            return "For";
        }

        @Override
        public String toStringTree() {
            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append(initExpr.toStringTree());
            buf.append(" ");
            buf.append(terminationExpr.toStringTree());
            if(incrementExpr != null){
                buf.append(" ");
                buf.append(incrementExpr.toStringTree());
            }
            buf.append(" ");
            buf.append(block.toStringTree());
            buf.append(")");
            return buf.toString();
        }
    }

    public static class ForInStat extends ForStat {
        public List<String> names;
        public List<ExprNode> exprs;

        public ForInStat(List<String> names, List<ExprNode> exprs) {
            this.names = names;
            this.exprs = exprs;
        }

        @Override
        public String toString() {
            return "ForIn";
        }

        @Override
        public String toStringTree() {
            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append(names);
            buf.append(" ");
            for(int i=0; i<exprs.size();  i++){
                buf.append(" ");
                buf.append(exprs.get(i).toStringTree());
            }
            buf.append(" ");
            buf.append(block.toStringTree());
            buf.append(")");
            return buf.toString();
        }
    }
}
