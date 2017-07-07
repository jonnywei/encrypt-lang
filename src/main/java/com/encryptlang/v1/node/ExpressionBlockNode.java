package com.encryptlang.v1.node;

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

}
