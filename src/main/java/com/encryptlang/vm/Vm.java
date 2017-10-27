package com.encryptlang.vm;

import static com.encryptlang.vm.Instruction.*;

/**
 *
 *
 *
 * memory
 +------------------+
 |    stack   |     |      high address
 |    ...     v     |
 |                  |
 |                  |
 |                  |
 |                  |
 |    ...     ^     |
 |    heap    |     |
 +------------------+
 | bss  segment     |
 +------------------+
 | data segment     |
 +------------------+
 | text segment     |      low address
 +------------------+

 *
 * register
 pc  program counter
 sp  stack pointer
 bp  base pointer
 ax  regular register

 *
 * instruction set

 LEA ,IMM ,JMP ,CALL,JZ  ,JNZ ,ENT ,ADJ ,LEV ,LI  ,LC  ,SI  ,SC  ,PUSH,
 OR  ,XOR ,AND ,EQ  ,NE  ,LT  ,GT  ,LE  ,GE  ,SHL ,SHR ,ADD ,SUB ,MUL ,DIV ,MOD ,
 OPEN,READ,CLOS,PRTF,MALC,MSET,MCMP,EXIT
 *
 */
public class Vm {

    public static final int MAX_SIZE = 1024*1024;

    public static int[] text;
    public static int[] data;
    public static  int[] stack;
    public static int pc ;
    public static int bp ;
    public static int sp ;
    public static int ax  ;
    public static int cycle = 0;


    public static void main(String[] args) {
         start();
         load();
         eval();
    }
    public static void load(){
        int i = 0;
        text[i++] = IMM;
        text[i++] = 10;
        text[i++] = PUSH;
        text[i++] = IMM;
        text[i++] = 20;
        text[i++] = ADD;
        text[i++] = PUSH;
        text[i++] = EXIT;
    }

    public static void start(){
        text = new int[MAX_SIZE];
        data = new int[MAX_SIZE];
        stack = new int[MAX_SIZE];
        pc =0;
        bp =sp = MAX_SIZE;
    }

    public static int eval(){
        int op=0;
        while(true){
            op = text[pc++];
            cycle++;
            if(op == Instruction.NOP){
                ;
            }
            else if(op == IMM){
                ax = text[pc++];
            }
            else if(op == Instruction.LC){
                ax = (char) data[ax];
            }
            else if(op == Instruction.LI){
                ax = data[ax];
            }
            else if(op == Instruction.SC){
                data[stack[sp++]]= (char)ax;
            }
            else if(op == Instruction.SI){
                data[stack[sp++]] = ax;
            }
            else if(op == Instruction.PUSH){
                stack[--sp] = ax;
            }
            else if(op == Instruction.JMP){
                 pc = text[pc];
            }
            else if(op == Instruction.JZ){
                pc =  ax==0? text[pc] : pc+1;
            }
            else if(op == Instruction.JNZ){
                pc =  ax!=0? text[pc] : pc+1;
            }

            else if(op == Instruction.CALL){
                stack[--sp] = pc+1;  pc = text[pc];
            }
            else if(op == Instruction.ENT){
                stack[--sp] =bp; bp =sp; sp= sp -text[pc++];
            }
            else if(op == Instruction.LEV){
                sp=bp;  bp = stack[sp++];  pc = stack[sp++];
            }
            //
            else if(op == Instruction.ADJ){
               sp = sp + text[pc++];
            }
            //
            else if(op == Instruction.LEA){
               ax = stack[bp + text[pc++]];
            }

            //
            else if(op == Instruction.ADD){
                ax = stack[sp++] + ax;
            }
            //
            else if(op == Instruction.GT){
                ax = stack[sp++] > ax ? 1:0;
            }
            //
            else if(op == Instruction.EXIT){
                System.out.printf("exit(%d)",stack[sp]);
                return stack[sp];
            }

        }
    }

}
