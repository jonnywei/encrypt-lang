package com.encryptlang.v1.node;

import com.encryptlang.v1.env.Environment;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public interface Node {

    Object eval(Environment env);

}
