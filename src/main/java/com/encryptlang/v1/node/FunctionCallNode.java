package com.encryptlang.v1.node;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class FunctionCallNode implements Node {

    private String fname;

    private Node expressionNode;

    public FunctionCallNode(String fname, Node expressionNode) {
        this.fname = fname;
        this.expressionNode = expressionNode;
    }
}
