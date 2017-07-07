package com.encryptlang.v0.node;

import com.encryptlang.v0.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public class AbstractFunction implements Function {

    private String name;

    private List<Token> paramList = new ArrayList<>();

    public AbstractFunction(String name, List<Token> paramList) {
        this.name = name;
        this.paramList = paramList;
    }


    @Override public Object apply(Object[] args) {
        return null;
    }

    @Override public Object eval(Environment env) {
        return null;
    }
}
