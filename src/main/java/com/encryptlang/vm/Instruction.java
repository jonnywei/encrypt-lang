package com.encryptlang.vm;

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

 NOP, LEA ,IMM ,JMP ,CALL,JZ,JNZ ,ENT ,ADJ ,LEV ,LI  ,LC  ,SI  ,SC  ,PUSH,
 OR  ,XOR ,AND ,EQ  ,NE  ,LT  ,GT  ,LE  ,GE  ,SHL ,SHR ,ADD ,SUB ,MUL ,DIV ,MOD ,
 OPEN,READ,CLOS,PRTF,MALC,MSET,MCMP,EXIT
 *
 */
public class Instruction {

    public static final int NOP =0;

    //IMM <num> move num to ax
    public static final int IMM =1;
    //load *(char *)ax to ax
    public static final int LC  =2;
    //load *(int *) ax to ax
    public static final int LI  =3;
    //store (char)ax to * stack[sp++]
    public static final int SC = 4;

    public static final int SI = 5;
    // push ax to stack[--sp]
    public static final int PUSH =6;
    // pc = <addr>
    public static final int JMP = 7;

    // jump if ax is zero
    public static final int JZ = 8;

    // jump if ax is't zero
    public static final int JNZ =9;

    //function call flow  | push arg->call->ent->lev->adj
    /**
     sub_function(arg1, arg2, arg3);

     |    ....       | high address
     +---------------+
     | arg: 1        |    new_bp + 4
     +---------------+
     | arg: 2        |    new_bp + 3
     +---------------+
     | arg: 3        |    new_bp + 2
     +---------------+
     |return address |    new_bp + 1
     +---------------+
     | old BP        |   <- new BP
     +---------------+
     | local var 1   |    new_bp - 1
     +---------------+
     | local var 2   |    new_bp - 2
     +---------------+
     |    ....       |  low address
     */
    // call function
    public static final int CALL =10;
    // enter make new call frame
    public static final int ENT =11;
    public static final int LEV =12;
    public static final int ADJ =13;
    public static final int LEA =14;


    public static final int ADD =33;

    public static final int GT = 65;

    public static final int EXIT = 128;
    public static final int OPEN = 129;
    public static final int CLOS = 130;
    public static final int READ = 131;
    public static final int PRTF = 132;





}
