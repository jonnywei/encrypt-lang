package com.encryptlang.assembly;

import com.encryptlang.v0.env.BaseEnvironment;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * simple assembler  https://github.com/skx/simple.vm
 *
 * json implement
 * .section .data
 *
 * .section .text
 *
 *
 * 第一版支持标签
 * 例如：
 *  :label
 *  jmp label
 *  jmp 0x222f
 *    CONST 6
 *  CALL fib, 1
               PRINT,
                 HALT,
 *  :fib
 *  *                 LOAD_ARG, 1,
 *  *                 CONST, 0,
 *  *                 EQ,
 *  *                 JMPF, 17,
 *  *                 CONST,0,
 *  *                 RET,
 *  *                 LOAD_ARG, 1,
 *
 *
 */
public class Assembler {


    private enum State{
        NORMAL,
        LABEL,
        INSTRUCTION,
        COMMENT
    }


    public  enum Type {
        LABEL,
        INSTRUCTION,
        COMMENT
    }

    private class Label{
        private String name;
        private int   position = -1;

    }


    private Map<String,Label>  labelMap = new HashMap<>();

    private Map<Integer, String> needChange = new HashMap<>();

    private int[] program ;

    private State state = State.NORMAL;

    private StringBuilder readBuffer;

    private StringReader file;

    public Assembler(StringReader file) {
        this.file = file;
    }

    // one pass
    public void parse(){

        try {
            int read = this.file.read();
            while(read != -1){
                char ch = (char) read;
                while(!parseChar(ch)){}
                read = this.file.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void refreshBuffer(char c){
        readBuffer = new StringBuilder();
        readBuffer.append(c);
    }

    private boolean parseChar(char ch){
        boolean moveCursor = true;
        boolean createToken = false;
        Type createType = Type.LABEL;
        switch (state){
            case NORMAL:
                refreshBuffer(ch);
                if (ch == ':') {
                    state = State.LABEL;
                    moveCursor = true;
                } else if( ch >= 'a' && ch <= 'z' || ch >='A' && ch <= 'Z'){
                    state = State.INSTRUCTION;
                    moveCursor = true;
                }else if (ch == '#'){
                    state = State.COMMENT;
                    moveCursor = true;
                }
                break;
            case LABEL:
                if(ch =='#' || ch >= 'a' && ch <= 'z' || ch >='A' && ch <= 'Z'
                    || ch >='0' && ch <='9' ){
                    readBuffer.append(ch);
                }else {
                    createToken = true;
                    createType = Type.LABEL;
                    state = State.NORMAL;
                    moveCursor = false;
                }
                break;
            case INSTRUCTION:
                if( ch == '\n' ) {
                    state = State.NORMAL;
                    createToken = true;
                    createType = Type.INSTRUCTION;
                } else if( ch == '#' ) {
                    createToken = true;
                    createType = Type.INSTRUCTION;
                    state = State.NORMAL;
                    moveCursor = false;
               }else {
                    readBuffer.append(ch);
                }
                break;
            case COMMENT:
                if( ch == '\n' ){
                    state = State.NORMAL;
                    moveCursor = true;
                    createType = Type.COMMENT;
                    createToken = true;
                }else {
                    readBuffer.append(ch);
                }
                break;
        }
        if(createToken){
            createToken(createType);
        }
        return moveCursor;
    }



    private void createToken(Type type){
        String name = readBuffer.toString();
        Type type1 = type;

        System.out.println(name + ":" + type );
    }


    public static void main(String[] args) {
        String asm = ":test\n" +
                ":label\n" +
                "goto  0x44ff      # Jump to the given address\n" +
                "goto  label       # Jump to the given label\n" +
                "jmpnz label       # Jump to label if Zero-Flag is not set\n" +
                "jmpz  label       # Jump to label if Zero-Flag is set";


        StringReader stringReader = new StringReader(asm);
        Assembler assembler = new Assembler(stringReader);
        assembler.parse();

    }

}
