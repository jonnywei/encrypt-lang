package com.encryptlang.v1.node;

import com.encryptlang.v1.Token;

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
}
