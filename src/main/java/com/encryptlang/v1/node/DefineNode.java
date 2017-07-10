package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class DefineNode implements Node {

    private String varName;

    private Node  expressionNode;

    public DefineNode(String varName, Node expressionNode) {
        this.varName = varName;
        this.expressionNode = expressionNode;
    }


    @Override public Object eval(Environment env) {
        Object result = expressionNode.eval(env);
        env.putValue(varName, result);
        return  result;
    }
}
