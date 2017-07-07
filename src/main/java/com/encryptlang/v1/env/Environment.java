package com.encryptlang.v1.env;



import java.util.HashMap;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class Environment {

    private final HashMap<String, Object> env = new HashMap<>();


    public Object getValue(String symbolForm){
        Object result = env.get(symbolForm);
        return result;
    }

    public Object putValue(String s, Object object){
        return env.put(s,object);
    }
}
