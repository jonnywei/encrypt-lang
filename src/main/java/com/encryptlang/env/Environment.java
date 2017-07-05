package com.encryptlang.env;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public class Environment {

    private Map<String , Object> env =  new HashMap<>();



    public Object getValue(String key){
        return env.get(key);

    }

    public Object putValue(String key ,String value){
        return env.put(key,value);
    }
}
