package com.encryptlang.lua.tree;

import com.encryptlang.lua.AST;

public interface ASTVisitor {

    void visit(AST ast);
}
