package com.encryptlang.v0.node;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public interface Token {

    final Token END_TOKEN = new Token() {
        @Override public Object eval(Object[] args) {
            return null;
        }
        @Override public String getName() {
            return "EOF";
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


    String getName();


    class  OpenParenthensis implements Token {

        @Override public Object eval(Object[] args) {
            return null;
        }

        @Override public String getName() {
            return String.valueOf(OPEN_PARENTHESIS);
        }
    }


     class  CloseParenthensis implements Token {

        @Override public Object eval(Object[] args) {
            return null;
        }
         @Override public String getName() {
             return null;
         }
    }

     class  Assign implements Token {

        @Override public Object eval(Object[] args) {
            return null;
        }
         @Override public String getName() {
             return String.valueOf(Token.ASSIGN);
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
         @Override public String getName() {
             return null;
         }

    }

    class WordToken implements Token {

        String  val ;

        public WordToken(String val) {
            this.val = val;
        }

        @Override public Object eval(Object[] args) {
            return val;
        }
        @Override public String getName() {
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
        @Override public String getName() {
            return null;
        }
    }
    class Comma implements Token{
        String  val  =",";

        public Comma() {
        }

        @Override public Object eval(Object[] args) {
            return val;
        }
        @Override public String getName() {
            return null;
        }
    }
}
