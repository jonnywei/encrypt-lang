package com.encryptlang.node;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public interface Token {

    public static final Token END_TOKEN = new Token() {
        @Override public Object eval(Object[] args) {
            return null;
        }
    };

    public static final  char DOUBLE_QUOTE ='"';
    public static final  char SINGLE_QUOTE ='\'';
    public static final  char ASSIGN ='=';
    public static final  char COMMAT =',';
    public static final  char OPEN_PARENTHESIS ='(';
    public static final  char CLOSE_PARENTHESIS =')';
    public static final  char PLUS ='+';


    /**
     * 表达式求值
     * @param args
     * @return
     */
    Object eval(Object[] args);



    class  OpenParenthensis implements Token {

        @Override public Object eval(Object[] args) {
            return null;
        }
    }


     class  CloseParenthensis implements Token {

        @Override public Object eval(Object[] args) {
            return null;
        }
    }

     class  Assign implements Token {

        @Override public Object eval(Object[] args) {
            return null;
        }
    }


     class StringToken implements Token {

        String  val ;

        public StringToken(String val) {
            this.val = val;
        }

        @Override public Object eval(Object[] args) {
            return val;
        }

    }

    class WordNode implements Token {

        String  val ;

        public WordNode(String val) {
            this.val = val;
        }

        @Override public Object eval(Object[] args) {
            return val;
        }

    }

    class PlusToken implements Token{
        String  val  ="+";

        public PlusToken(){
        }

        @Override public Object eval(Object[] args) {
            return val;
        }
    }
    class Comma implements Token{
        String  val  =",";

        public Comma() {
        }

        @Override public Object eval(Object[] args) {
            return val;
        }
    }
}
