package com.encryptlang.v1.env;

import com.encryptlang.v1.node.Function;

/**
 * Created by wjj on 6/11/17.
 */
public class BaseEnvironment {
    public static final String DES_STR ="DES";
    public static final String AES_STR ="AES";

    public   static  abstract  class BuiltinFunction implements Function {

        private String name;

        public BuiltinFunction(String name) {
            this.name = name;
        }

        @Override public Object apply(Object... args) {
            return null;
        }

        @Override public Object eval(com.encryptlang.v0.env.Environment env) {
            return this;
        }

        @Override public String toString() {
            return "<procedure:"  + name  + '>';
        }

    }

    private static final Function DES  =  new BuiltinFunction(DES_STR){
        @Override public Object apply(Object... args) {
            return new String (DES_STR +"-apply-" + args.toString());
        }
    };

    private static final Function AES  =  new BuiltinFunction(AES_STR){
        @Override public Object apply(Object... args) {
            return new String (AES_STR+"-apply-" + args.toString());
        }
    };

    public static Environment getBaseEnvironment(){
         Environment environment = new Environment();
         environment.putValue(DES_STR,DES);
         environment.putValue(AES_STR,AES);
        return environment;
    }
}
