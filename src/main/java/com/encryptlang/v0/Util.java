package com.encryptlang.v0;

/**
 * Created by wjj on 6/15/17.
 */
public class Util {

    public static class List {
        private Object[] elems ;
        public List(Object... args) {
            elems = args;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("'(");
            int idx =0;
            for(Object arg : elems){
                sb.append(arg);
                idx++;
                if(elems.length != idx){
                    sb.append(" ");
                }
            }
            sb.append(")");
            return  sb.toString();
        }
    }
}
