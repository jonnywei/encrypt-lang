package com.encryptlang.lua.tree;

import com.encryptlang.lua.AST;
import com.encryptlang.lua.ast.StatNode;

/**
 * print visitor
 */
public class PrintVisitor implements ASTVisitor {
    @Override
    public void visit(AST ast) {
        if(ast instanceof StatNode){

        }
        System.out.println(  ast.toStringTree());
    }


}
