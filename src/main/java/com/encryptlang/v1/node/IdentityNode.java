package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class IdentityNode implements Node {

    private String name;

    public IdentityNode(String name) {
        this.name = name;
    }


    @Override public Object eval(Environment env) {
        return env.getValue(name);
    }
}
