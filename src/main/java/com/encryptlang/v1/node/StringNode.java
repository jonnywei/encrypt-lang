package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class StringNode implements Node {

    private String value;

    public StringNode(String value) {
        this.value = value;
    }


    @Override public Object eval(Environment env) {
        return value;
    }
}
