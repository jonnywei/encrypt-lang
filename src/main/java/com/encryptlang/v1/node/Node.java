package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public interface Node {

    Node EMPTY = new Node() {
        @Override public Object eval(Environment env) {
            return null;
        }
    };

    Object eval(Environment env);

}
