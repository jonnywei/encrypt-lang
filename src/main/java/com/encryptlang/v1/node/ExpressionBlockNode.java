package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class ExpressionBlockNode implements Node{

    public List<Node>  list = new ArrayList<>();


    public boolean add(Node node){
        return list.add(node);
    }

    @Override public Object eval(Environment env) {
        Object val =null;
        for(Node node : list){
            if(node != null){
                val=   node.eval(env);
            }
        }
        return val;
    }
}
