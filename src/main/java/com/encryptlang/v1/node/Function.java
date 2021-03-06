package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public interface Function {

    /**
     * apply function
     * @param args
     * @return
     */
    Object apply(Object... args);


    Object eval(Environment env);
}
