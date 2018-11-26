package com.encryptlang.json;

public class JsonLexer extends Lexer {

    public static int ID  = 2;
    public static int COMMA  = 3;
    public static int LBRACK  = 4;
    public static int RBRACK  = 5;
    public static int LBRACE  = 6;  // "{"
    public static int RBRACE  = 7;
    public static int COLON  = 8;
    public static int TRUE  = 9;
    public static int FALSE = 10;
    public static int NULL = 11;
    public static int NUMBER = 12;
    public static int STRING = 13;
    public static Object JSON_NULL = new Object();

    public static String[] tokenNames = {"n/a","<EOF>","ID","COMMA",
    "LBRACK","RBRACK","LBRACE","RBRACE","COLON","TRUE","FALSE","NULL","NUMBER",
            "STRING"};


    public JsonLexer(String input) {
        super(input);
    }


    @Override
    public Token nextToken() {
        while ( c != EOF){
           if(include(Space,c)) { //
               skipWs();
           }else if( c ==','){
               consume(); return new Token(COMMA,",");
           }else if( c ==':'){
            consume(); return new Token(COLON,":");
           } else if( c =='{'){
               consume(); return new Token(LBRACE,"{");
           }else if( c =='}'){
               consume(); return new Token(RBRACE,"}");
           }else if( c =='['){
               consume(); return new Token(LBRACK,"[");
           }else if( c ==']'){
               consume(); return new Token(RBRACK,"]");
           }else if( c ==':') {
               consume();
               return new Token(COLON, ":");
           }else if(isIdFirstChar()){
               return ID();
           }else if(c =='-' ){ //number or sign
               if(isNumberChar(nextChar(1))){ //only peek one char
                   return NUMBER();
               }
           }else if(c >= '0' & c <= '9'){
               return NUMBER();
           }else if(c =='"' || c=='\''){
               return STRING();
           }else {
               throw new Error("invalid char :"+ c + " line " +line);
           }

        }
        return new Token(EOF_TYPE,"<EOF>");
    }

    @Override
    public String getTokenName(int tokenType) {
        return tokenNames[tokenType];
    }

    private void skipWs(){
        while (include(Space, c)) {
            if (c == '\n'){
                line ++;
            }
            consume();
        }
    }


    private boolean isIdFirstChar( ){
        return  (c >= 'a' &&  c <= 'z') | (c >= 'A' &&  c <= 'Z' )
                |  c == '_';
    }
    private boolean isIdChar( ){
        return  (c >= 'a' &&  c <= 'z') | (c >= 'A' &&  c <= 'Z' )
                |  c == '_' | c >= '0' & c <= '9';
    }

    private boolean isNumberChar( char c){
        return   c >= '0' & c <= '9';
    }

    private static final char[] Space = new char[] {' ', '\t','\n','\r'};

    private boolean include(char[] range, char c){
        boolean include = false;

        for(char m : range){
            if(m == c){
                include = true;
                break;
            }
        }
        return include;
    }


    private Token ID(){
        StringBuilder buf = new StringBuilder();
        do{
            buf.append(c);
            consume();
        }while (isIdChar());
        String id = buf.toString();
        if(id.equals("true")){
             return new Token(TRUE, id);
        }else if(id.equals("false")){
            return new Token(FALSE, id);
        }else if(id.equals("null")){
            return new Token(NULL, id);
        }
        throw new Error("expect true false null ,but found "+ id + " in line "+ line);
    }


    private Token NUMBER(){
        StringBuilder buf = new StringBuilder();
        if(c=='-'){
            buf.append(c);consume();
        }
        do{
            buf.append(c);consume();
        }while(isNumberChar(c));
        if(c=='.'){
            buf.append(c);consume();
            if(!isNumberChar(c)){
                throw new Error("expect number,but found "+c + "line "+ line);
            }
            while(isNumberChar(c)){
                buf.append(c);consume();
            }
        }
        return new Token(NUMBER, buf.toString());
    }



    private Token STRING(){
        StringBuilder buf = new StringBuilder();
        char begin = c;
        consume();
        while( c != begin){
            buf.append(c);consume();
        }
        if(c == begin){
            consume();
        }else {
            throw new Error("expect char "+ begin + ",but "+ c);
        }
        return new Token(STRING, buf.toString());
    }
}
