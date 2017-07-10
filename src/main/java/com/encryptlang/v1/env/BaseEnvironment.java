package com.encryptlang.v1.env;

import com.encryptlang.v0.Util;
import com.encryptlang.v1.node.Function;

/**
 * Created by wjj on 6/11/17.
 */
public class BaseEnvironment {
    public static final String DES_STR ="DES";
    public static final String AES_STR ="AES";
    public static final String APPEND_STR ="APPEND";

    public   static  abstract  class BuiltinFunction implements Function {

        private String name;

        public BuiltinFunction(String name) {
            this.name = name;
        }

        @Override public Object apply(Object... args) {
            return null;
        }

        @Override public Object eval(Environment env) {
            return this;
        }

        @Override public String toString() {
            return "<procedure:"  + name  + '>';
        }

    }

    private static final Function DES  =  new BuiltinFunction(DES_STR){
        @Override public Object apply(Object... args) {
            Util.List list = new Util.List(args);
            return new String (DES_STR +" " +  list);
        }
    };

    private static final Function AES  =  new BuiltinFunction(AES_STR){
        @Override public Object apply(Object... args) {
            Util.List list = new Util.List(args);

            return new String (AES_STR+" " + list);
        }
    };

    private static final Function APPEND  =  new BuiltinFunction(APPEND_STR){
        @Override public Object apply(Object... args) {
            if(args.length < 2){
                throw new IllegalArgumentException("append function need two params");
            }
            StringBuilder result = new StringBuilder();
            for(int i=0; i < args.length -1; i++){
                result.append(args[i]);
            }
            return  result.toString();
        }
    };
    public static Environment getBaseEnvironment(){
         Environment environment = new Environment();
         environment.putValue(DES_STR,DES);
         environment.putValue(AES_STR,AES);
         environment.putValue(APPEND_STR,APPEND);

        return environment;
    }
}
