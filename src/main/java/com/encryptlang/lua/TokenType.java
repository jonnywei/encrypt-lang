package com.encryptlang.lua;

public enum TokenType {
    EOF("EOF"),
    ID("ID"),
    NUMBER("NUMBER"),
    STRING("STRING"),

    COMMA("COMMA"),         // ,
    LBRACK("LBRACK"),       // [
    RBRACK("RBRACK"),       // ]
    LBRACE("LBRACE"),       // {
    RBRACE("RBRACE"),       // }
    COLON("COLON"),         // :
    LABEL("LABEL"),         // ::
    LPAREN("LPAREN"),       // (
    RPAREN("RPAREN"),       // )
    DOT("DOT"),             // .
    CONCAT("CONCAT"),       // ..
    VARARG("VARARG"),       // ...
    SEMI("SEMI"),           // ;

    //binary operators
    ADD("ADD"),            // +
    SUB("SUB"),            // -   // same as unary minus default
    DIV("DIV"),            // /
    IDIV("IDIV"),          // //
    MUL("MUL"),            // *
    POWER("POWER"),        // ^
    MOD("MOD"),            // %
    GT("GT"),              // >
    GE("GE"),              // >=
    SHR("SHR"),            // >>
    LT("LT"),              // <
    LE("LE"),              // <=
    SHL("SHL"),            // <<
    ASSIGN("ASSIGN"),      // =
    EQUAL("EQUAL"),        // ==
    NEQUAL("NEQUAL"),      // ~=
    BAND("BITWISE_AND"),   // &
    BOR("BITWISE_OR"),     // |
    // xor
    BXOR("BITWISE_XOR"),   // ~    default  binary operator  xor  same as bitwise not

    //keywords
    NIL("NIL"),
    FALSE("FALSE"),
    TRUE("TRUE"),
    DO("DO"),
    END("END"),            // end
    WHILE("WHILE"),
    REPEAT("REPEAT"),
    UNTIL("UNTIL"),
    IF("IF"),
    THEN("THEN"),
    ELSEIF("ELSEIF"),
    ELSE("ELSE"),
    FOR("FOR"),
    IN("IN"),
    FUNCTION("FUNCTION"), // function
    LOCAL("LOCAL"),
    BREAK("BREAK"),       // break
    GOTO("GOTO"),         // goto
    RETURN("RETURN"),

    // logical operators
    AND("AND"),           // and
    OR("OR"),             // or
    //unary operators
    NOT("NOT"),           // not


    // unary operators
    UNMINUS("UNMINUS"),   // -
    BNOT ("BITWISE_NOT"), // ~     unary operators   bitwise not
    LENGTH ("LENGTH"),    // #


    ;

    private String typeName;

    TokenType(String name) {
        this.typeName = name;
    }

    public static void main(String[] args) {
        TokenType.ID.ordinal();
    }
}
