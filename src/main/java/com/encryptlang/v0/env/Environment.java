package com.encryptlang.v0.env;


import com.encryptlang.v0.form.Form;

import java.util.HashMap;

/**
 *
 * environment
 *
 * Created by wjj on 6/11/17.
 */
public class Environment {
    private final HashMap<Form.SymbolForm, Object> env = new HashMap<>();

    private Environment parent; //parent

    public Environment() {
    }

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public Object getValue(Form.SymbolForm symbolForm){
        Object result = env.get(symbolForm);
        if(parent != null &&  result == null ){
            return  parent.getValue(symbolForm);
        }
        return result;
    }

    public Object putValue(Form.SymbolForm symbolForm, Object object){
        return env.put(symbolForm,object);
    }
}
