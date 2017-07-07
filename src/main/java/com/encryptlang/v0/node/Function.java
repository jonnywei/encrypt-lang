package com.encryptlang.v0.node;

import com.encryptlang.v0.env.Environment;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public interface Function  {

    /**
     * apply function
     * @param args
     * @return
     */
    Object apply (Object... args);


    Object eval(Environment env);
}
