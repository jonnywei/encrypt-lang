package com.encryptlang.v1.node;

import com.encryptlang.v1.Token;
import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class AddNode implements Node {

    private Token first;

    private Node last;

    public AddNode(Token first, Node last) {
        this.first = first;
        this.last = last;
    }

    @Override public Object eval(Environment env) {
        return first.value + last.eval(env);
    }
}
