package com.encryptlang.svm;

import static com.encryptlang.svm.OpCode.*;

public class StackVM {

    // code
    int[] code;

    //data
    int[] data;

    int[] stack;

    int sp; // stack pointer

    int pc; // program counter

    int fp; // frame pointer

    boolean running;
    //创建vm
    public StackVM(int[] code ,int pc, int dataSize, int stackSize ){

        // load code
        this.code =new int[code.length];
        System.arraycopy(code,0, this.code, 0, code.length);

        //init data and stack
        this.data = new int[dataSize];
        this.stack = new int[stackSize];

        this.pc = pc;
        this.sp = -1 ;
        this.fp = 0;
        this.running = false;
    }



    void run(){
        running = true;
        do {
            //fetch code
            int opcode = this.code[this.pc++];
            int a,b,v, addr;
            switch (opcode) { //decode

                case HALT: running = false; break;
                case PRINT:
                    v = this.stack[this.sp--];
                    System.out.println(v);
                    break;
                case ADD:
                    b = this.stack[this.sp--];
                    a = this.stack[this.sp--];
                    this.stack[++this.sp] = (a + b);
                    break;
                case CONST:
                    v = this.code[this.pc++];
                    this.stack[++this.sp] = v;
                    break;
                case JMP:  // unconditional  jump
                    this.pc = this.code[this.pc++];
                    break;
                case JMPT:
                    v  =  this.code[this.pc++];
                    a = this.stack[this.sp--];
                    if(a != 0){
                        this.pc = v;
                    }
                    break;
                case LOAD:
                    addr = this.stack[this.sp--];
                    this.stack[++ this.sp ] = this.data[addr];
                    break;
                case STORE:
                    addr = this.stack[this.sp--];
                    this.data[addr] = this.stack[++ this.sp ] ;
                case CALL:
                    doCall();break;
                case RET:
                    doRet(); break;

            }


        }while (running);
    }


    void doCall(){
        int addr, argc;
        addr = this.code[this.pc++];
        argc = this.code[this.pc++];
        this.stack[++this.sp ] = argc;
        this.stack[++this.sp ] = this.pc; //return address
        this.stack[++this.sp ] = this.fp; // old fp push
        this.fp = this.sp;
        this.pc = addr;
    }


    void doRet(){
        int  argc,rval;
        rval = this.stack[this.sp--];
        this.sp = this.fp;
        this.fp =this.stack[this.sp --]; // old fp push
        this.pc = this.stack[this.sp --]; //return address
        argc = this.stack[this.sp --];
        this.sp  = this.sp- argc;
        this.stack[ ++ this.sp] = rval;
    }


    public static void main(String[] args) {
        int[] program = new int[]{
                CONST,6,
                CALL ,7, 1,
                PRINT,
                HALT,
                CONST,128,
                RET
        };

        StackVM vm = new StackVM(program,0, 0,1024);
        vm.run();
    }
}
