package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/10.
 */
public class ListNode implements Node {

    private List<Node> nodeList  = new ArrayList<>();


    public boolean addNode(Node n){
     return  this.nodeList.add(n);
    }

    @Override public Object[] eval(Environment env) {

        List<Object> evalParm = new ArrayList<>();
        for(Node node :nodeList){
            evalParm.add(node.eval(env));
        }
        return evalParm.toArray();
    }


}
