package com.encryptlang.lua.ast;


import java.util.List;

// if exp then block {elseif exp then block} [else block] end
public class IfStat  extends StatNode {

    public ExprNode expr;
    public Block block;
    public List<ElseIfStat> elseIfs;
    public Block elseBlock;

    @Override
    public String toString() {
        return "IF";
    }

    @Override
    public String toStringTree() {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(expr.toStringTree());
        buf.append(" ");
        buf.append(block.toStringTree());
        for(int i=0; i<elseIfs.size();  i++){
            buf.append(" ");
            buf.append(elseIfs.get(i).toStringTree());
        }
        if(elseBlock != null){
            buf.append(" ");
            buf.append(elseBlock.toStringTree());
        }
        buf.append(")");
        return buf.toString();
    }


    public static class ElseIfStat extends IfStat{

        public ElseIfStat(ExprNode expr,Block block) {
            this.expr = expr;
            this.block = block;
        }

        @Override
        public String toString() {
            return "ELSEIF";
        }

        @Override
        public String toStringTree() {
            StringBuilder buf = new StringBuilder();
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
            buf.append(expr.toStringTree());
            buf.append(" ");
            buf.append(block.toStringTree());
            buf.append(")");
            return buf.toString();
        }
    }
}
