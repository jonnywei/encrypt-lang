package com.encryptlang.v1.node;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class AssignNode implements Node {

    private String varName;

    private Node  expressionNode;

    public AssignNode(String varName, Node expressionNode) {
        this.varName = varName;
        this.expressionNode = expressionNode;
    }
}
