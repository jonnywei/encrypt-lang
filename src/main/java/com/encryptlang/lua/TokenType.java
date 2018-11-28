package com.encryptlang.lua;

public enum TokenType {
    EOF("EOF"),
    ID("ID"),
    NUMBER("NUMBER"),
    STRING("STRING"),
    NIL("NIL"),
    FALSE("FALSE"),
    TRUE("TRUE"),
    COMMA("COMMA"),         // ,
    LBRACK("LBRACK"),       // [
    RBRACK("RBRACK"),       // ]
    LBRACE("LBRACE"),       // {
    RBRACE("RBRACE"),       // }
    COLON("COLON"),         // :
    LABEL("LABEL"),         // ::
    LPAREN("LPAREN"),       // (
    RPAREN("RPAREN"),       // )
    DOT("DOT"),            // .
    CONCAT("CONCAT"),      // ..
    VARARG("VARARG"),      // ...
    SEMI("SEMI"),
    ADD("ADD"),            // +
    SUB("SUB"),            // -
    DIV("DIV"),
    MUL("MUL"),
    GT("GT"),              // >
    GE("GE"),              // >=
    SHR("SHR"),            // >>
    LT("LT"),              // <
    LE("LE"),              // <=
    SHL("SHL"),            // <<
    NOT("NOT"),
    ASSIGN("ASSIGN"),      // =
    EQUAL("EQUAL"),        // ==
    NEQUAL("NEQUAL"),      // ~=
    SHARP("#"),            // #

    //keywords
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

    FUNCTION("FUNCTION"),
    LOCAL("LOCAL"),
    RETURN("RETURN"),
    AND("AND"),
    OR("OR"),
    UNMINUS("UNMINUS"),      // -

    ;

    private String typeName;

    TokenType(String name) {
        this.typeName = name;
    }

    public static void main(String[] args) {
        TokenType.ID.ordinal();
    }
}
