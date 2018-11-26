package com.encryptlang.lua;

public class LuaLexer extends Lexer {

    public LuaLexer(String input) {
        super(input);
    }

    @Override
    public Token nextToken() {
        while ( c != EOF){
           if(include(Space,c)) { //
               skipWs();
           }else if( c ==','){
               consume(); return new Token(TokenType.COMMA,",");
           }else if( c ==':'){
            consume(); return new Token(TokenType.COLON,":");
           } else if( c =='{'){
               consume(); return new Token(TokenType.LBRACE,"{");
           }else if( c =='}'){
               consume(); return new Token(TokenType.RBRACE,"}");
           }else if( c =='['){
               consume(); return new Token(TokenType.LBRACK,"[");
           }else if( c ==']'){
               consume(); return new Token(TokenType.RBRACK,"]");
           }else if( c =='('){
               consume(); return new Token(TokenType.LPAREN,"(");
           }else if( c ==')'){
               consume(); return new Token(TokenType.RPAREN,")");
           }else if( c =='+'){
               consume(); return new Token(TokenType.ADD,"+");
           }else if( c =='-'){
               consume(); return new Token(TokenType.SUB,"-");
           }else if( c =='*'){
               consume(); return new Token(TokenType.MUL,"*");
           }else if( c =='#'){
               consume(); return new Token(TokenType.SHARP,"#");
           }else if( c ==';'){
               consume(); return new Token(TokenType.SEMI,";");
           }
           else if( c ==':') {
               consume();
               if(c  == ':'){ // match label
                   consume();
                   return new Token(TokenType.LABEL, "::");
               }else {
                   return new Token(TokenType.COLON, ":");
               }
           }
           else if( c =='.') {
               if(nextChar(1) == '.' && nextChar(2) == '.'){ // match ...
                   consume(); consume();consume();
                   return new Token(TokenType.VARARG, "...");
               }else  if(nextChar(1) == '.'){
                   consume(); consume();
                   return new Token(TokenType.CONCAT, "..");
               }else  {
                   consume();
                   return new Token(TokenType.DOT, ".");
               }
           }

           else if( c =='<') {
               if(nextChar(1) == '<'){ // match label
                   consume();consume();
                   return new Token(TokenType.SHL, "<<");
               } else if(nextChar(1) == '='){ // match label
                   consume();consume();
                   return new Token(TokenType.LE, "<=");
               }else {
                   consume();
                   return new Token(TokenType.LT, "<");
               }
           }
           else if( c =='>') {
               if(nextChar(1) == '>'){ // match label
                   consume();consume();
                   return new Token(TokenType.SHR, ">>");
               } else if(nextChar(1) == '='){ // match label
                   consume();consume();
                   return new Token(TokenType.GE, ">=");
               }else {
                   consume();
                   return new Token(TokenType.GT, ">");
               }
           }
           else if( c =='=') {
               if(nextChar(1) == '='){ // match equal
                   consume();consume();
                   return new Token(TokenType.EQUAL, "==");
               } else {
                   consume();
                   return new Token(TokenType.ASSIGN, "=");
               }
           }
           else if( c =='~') {
               if(nextChar(1) == '='){ // match equal
                   consume();consume();
                   return new Token(TokenType.NEQUAL, "~=");
               } else {
                   throw new Error("invalid char "+ c + " in line "+ line);
               }
           }
           else if(isIdFirstChar()){
               return ID();
           }else if(c >= '0' & c <= '9' || c =='.'){
               return NUMBER();
           }else if(c =='"' || c=='\''){
               return STRING();
           }else {
               throw new Error("invalid char "+ c + " in line "+ line);
           }

        }
        return new Token(TokenType.EOF,"<EOF>");
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

    //identifier
    private Token ID(){
        StringBuilder buf = new StringBuilder();
        do{
            buf.append(c);
            consume();
        }while (isIdChar());
        // keywords process
        String id = buf.toString();
        Token keyWord = Token.getKeyWord(id);
        if( keyWord != null){
            return keyWord;
        }
        return new Token(TokenType.ID, id);
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
                throw new Error("expect number,but "+c);
            }
            while(isNumberChar(c)){
                buf.append(c);consume();
            }
        }
        return new Token(TokenType.NUMBER, buf.toString());
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
        return new Token(TokenType.STRING, buf.toString());
    }
}
