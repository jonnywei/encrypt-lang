package com.encryptlang.svm;


public class   OpCode {


    public static final int NOP =0;

    /**
     * math operation
     */
    // int Add
    public static final int ADD =1;

    // int SUB
    public static final int SUB =2;

    /**
     * compare
     */
    public static final int LT = 10;

    public static final int EQ = 11;


    /**
     * jump
     */
    public static final int JMP = 20;

    public static final int JMPT = 21;

    public static final int JMPF = 22;



    /**
     * stack op
     */

    /**
     * store load
     */

    public static final int LOAD  = 30;

    public static final int STORE  = 31;

    public static final int POP  = 40;
    //push constant integer
    public static final int CONST =41;


    /**
     * subroutine
     */

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


    public static final int CALL = 50;

    public static final int RET = 51;

    public static final int LOAD_ARG = 52;

    /**
     * Other
     */

    public static final int HALT = 60;

    public static final int PRINT = 61;

}
