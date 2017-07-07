package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

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


    @Override public Object eval(Environment env) {
        Object param =  expressionNode.eval(env);
        Function function = (Function) env.getValue(fname);
        return function.apply(param);
    }

}
