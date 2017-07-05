package com.encryptlang.node;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public interface Function extends Token {

    /**
     * 函数求值
     * @param args
     * @return
     */
    Object apply(Object[] args);
}
