package com.encryptlang.lua;

import com.encryptlang.lua.ast.*;
import com.encryptlang.lua.ast.expr.*;
import com.encryptlang.lua.ast.stat.*;

import java.util.ArrayList;
import java.util.List;

public class LuaParser extends LLkParser {

    public LuaParser(LuaLexer lexer) {
        super(lexer,5);
    }

    public Block block(){
        Block block = new Block();
        block.statements = statements();
        return block;
    }


    public List<StatNode> statements(){
        List<StatNode> statements = new ArrayList<>();
        while(! isBlockEnd()){
            statements.add(statement());
        }
        return statements;
    }

    /**
     * stat ::=  ‘;’ |
     * 		 varlist ‘=’ explist |
     * 		 functioncall |
     * 		 label |
     * 		 break |
     * 		 goto Name |
     * 		 do block end |
     * 		 while exp do block end |
     * 		 repeat block until exp |
     * 		 if exp then block {elseif exp then block} [else block] end |
     * 		 for Name ‘=’ exp ‘,’ exp [‘,’ exp] do block end |
     * 		 for namelist in explist do block end |
     * 		 function funcname funcbody |
     * 		 local function Name funcbody |
     * 		 local namelist [‘=’ explist]
     *
     *
     * 		 label ::= ‘::’ Name ‘::’
     * @return
     */
    public StatNode statement(){
        switch (LA(1)){
            case DO:           return doStatement();
            case SEMI:         return emptyStatement();
            case WHILE:        return whileStatement();
            case IF:           return ifStatement();
            case REPEAT:       return repeatStatement();
            case FOR:          return  forStatement();
            case FUNCTION:     return functionStatement();
            case GOTO:         return  gotoStatement();
            case BREAK:       match(TokenType.BREAK); return new BreakStat();
            case LABEL:        return  labelStatement();
            case LOCAL:
                if(this.LA(2) == TokenType.FUNCTION){  //local function
                    return localFunctionStatement();
                }else {                                 // local declarations
                    return localDeclarationStatement();
                }
            case ID:
                System.out.println((this.LT(2)));
                if(this.LA(2) == TokenType.ASSIGN ){
                    return  assignStatement();
                }else if (this.LA(2) == TokenType.LPAREN ){
                    return  funcCallStatement();
                }

        }
        throw new Error(""+ LT(1));
//        StatNode stat = new StatNode();
//        return stat;
    }

    public DoStat doStatement(){
        match(TokenType.DO);
        Block block = block();
        match(TokenType.END);
        return new DoStat(block);
    }
    public StatNode emptyStatement(){
        match(TokenType.SEMI);
        StatNode stat = new EmptyStat();
        return stat;
    }

    public StatNode gotoStatement(){
        match(TokenType.GOTO);
        String name = LT(1).text;
        match(TokenType.ID);
        GotoStat stat = new GotoStat(name);
        return stat;
    }

    public StatNode labelStatement(){
        match(TokenType.LABEL);
        String name = LT(1).text;
        match(TokenType.ID);
        match(TokenType.LABEL);
        StatNode stat = new LabelStat(name);
        return stat;
    }

    public WhileStat whileStatement(){
        match(TokenType.WHILE);
        ExprNode expr = expr();
        match(TokenType.DO);
        Block block = block();
        match(TokenType.END);
        return new WhileStat(expr,block);
    }

    // if exp then block {elseif exp then block} [else block] end
    public IfStat ifStatement(){
        IfStat ifStat = new IfStat();
        match(TokenType.IF);
        ifStat.expr = expr();
        match(TokenType.THEN);
        ifStat.block = block();
        List<IfStat.ElseIfStat> elseIfStats = new ArrayList<>();
        while(LA(1) == TokenType.ELSEIF){
            match(TokenType.ELSEIF);
            ExprNode elseExpr = expr();
            match(TokenType.THEN);
            Block blockExpr = block();
            IfStat.ElseIfStat elseIfStat = new IfStat.ElseIfStat(elseExpr, blockExpr);
            elseIfStats.add(elseIfStat);
        }
        ifStat.elseIfs = elseIfStats;
        if(LA(1) == TokenType.ELSE){
            match(TokenType.ELSE);
            Block elseBlock = block();
            ifStat.elseBlock = elseBlock;
        }
        match(TokenType.END);
        return ifStat;
    }

    public RepeatStat repeatStatement(){
        match(TokenType.REPEAT);
        Block block = block();
        match(TokenType.UNTIL);
        ExprNode expr = expr();
        return new RepeatStat(expr,block);
    }

    /**
     * function funcname funcbody |
     * funcname ::= Name {‘.’ Name} [‘:’ Name]
     * @return
     */
    public FuncStat functionStatement(){
        match(TokenType.FUNCTION);
        String colonName =null;
        List<String> dotNames = new ArrayList<>();
        String name = LT(1).text;
        match(TokenType.ID);
        dotNames.add(name);
        while(LA(1) == TokenType.DOT){
            match(TokenType.DOT);
            dotNames.add(LT(1).text);
            match(TokenType.ID);
        }
        if(LA(1) == TokenType.COLON){
            match(TokenType.COLON);
            colonName =(LT(1).text);
            match(TokenType.ID);
        }
        FuncStat funcStat = new FuncStat(colonName,dotNames);
        funcStat.funcBody = funcbody();
        return funcStat;
    }

    /**
     * local function Name funcbody
     *
     * @return
     */
    public LocalFuncStat localFunctionStatement(){
        match(TokenType.LOCAL);
        match(TokenType.FUNCTION);
        String name = LT(1).text;
        match(TokenType.ID);
        return new LocalFuncStat(name,funcbody());

    }


    /**
     * local namelist [‘=’ explist]
     * namelist ::= Name {‘,’ Name}
     * 	explist ::= exp {‘,’ exp}
     * @return
     */
    public LocalDeclStat localDeclarationStatement(){
        match(TokenType.LOCAL);
        List<String> names = new ArrayList<>();
        names.add( LT(1).text);
        match(TokenType.ID);
        while (LA(1) == TokenType.COMMA ){
            match(TokenType.COMMA);
            names.add( LT(1).text);
            match(TokenType.ID);
        }
        match(TokenType.ASSIGN);
        List<ExprNode> exprs = new ArrayList<>();
        exprs.add( expr());
        while (LA(1) == TokenType.COMMA ){
            match(TokenType.COMMA);
            exprs.add( expr());
        }
        return new LocalDeclStat(names,exprs);

    }


    public FuncCallStat funcCallStatement(){
        String name = LT(1).text;
        match(TokenType.ID);
        return new FuncCallStat(name,funcArgs());
    }


    public AssignStat assignStatement(){
        String name = LT(1).text;
        match(TokenType.ID);
        match(TokenType.ASSIGN);
        ExprNode expr = expr();
        return new AssignStat(name,expr);
    }

    /**
     * 可以通过lookahead判断 la(2) is ASSIGN  for
     *                              IN COMMA for in
     * @return
     */
    // for Name ‘=’ exp ‘,’ exp [‘,’ exp] do block end |
// for namelist in explist do block end |
//	namelist ::= Name {‘,’ Name}
// 	explist ::= exp {‘,’ exp}
    public ForStat forStatement(){
        match(TokenType.FOR);
        if(this.LA(2) == TokenType.ASSIGN){
            String name = this.LT(1).text;
            match(TokenType.ID);
            match(TokenType.ASSIGN);
            ExprNode initExpr = expr();
            match(TokenType.COMMA);
            ExprNode termExpr = expr();
            ForStat.ForInitStat forStat = new ForStat.ForInitStat(name,initExpr,termExpr);
            if(LA(1) == TokenType.COMMA){
                match(TokenType.COMMA);
                forStat.incrementExpr= expr();
            }
            match(TokenType.DO);
            forStat.block = block();
            match(TokenType.END);
            return forStat;
        }else {
            List<String> nameList = new ArrayList<>();
            String name = this.LT(1).text;
            match(TokenType.ID);
            nameList.add(name);
            while (LA(1) == TokenType.COMMA){
                match(TokenType.COMMA);
                name = this.LT(1).text;
                match(TokenType.ID);
                nameList.add(name);
            }
            match(TokenType.IN);
            List<ExprNode> exprList = new ArrayList<>();
            ExprNode expr = expr();
            exprList.add(expr);
            while (LA(1) == TokenType.COMMA){
                match(TokenType.COMMA);
                expr = expr();
                exprList.add(expr);
            }
            ForStat.ForInStat forStat = new ForStat.ForInStat(nameList,exprList);
            match(TokenType.DO);
            forStat.block = block();
            match(TokenType.END);
            return forStat;
        }
    }



    //exp 处理二元操作符

    /**
     *  exp ::=  nil | false | true | Numeral | LiteralString | ‘...’ | functiondef |
     * 		 prefixexp | tableconstructor | exp binop exp | unop exp
     *
     * 	expr ::= expr0
     *
     * 	expr0 ::= exprn { binop exprn }
     *
     * 	exprn ::=  nil | false | true | Numeral |LiteralString | unop expr
     *
     *
     * 	//
     *
     * 	    expr ::= expr0
     *
     *     expr0 ::= expr1 { + | - expr1 }
     *
     * 	expr1 ::= expr2 { * | / expr2 }
     *
     * 	expr2 ::= expr3 { ^  expr3 }
     *
     * 	expr3 ::= exprn | unop exprn
     *
     *     exprn ::=  nil | false | true | Numeral |LiteralString
     *
     * @return
     */

    public ExprNode expr(){
        return exprPrecedenceClimbing();
//        return expr0();
    }

     //  expr0 ::= expr1 { + | - expr1 }
    private ExprNode expr0(){
        ExprNode expr = exprn();
        while(LA(1) == TokenType.ADD ||LA(1) == TokenType.SUB){
            TokenType op = this.LA(1);
            match(op); //消耗掉
            expr = new BinopExpr(op,expr, expr1());
        }
        return  expr;
    }

//     * 	expr1 ::= expr2 { * | / expr2 }
    private ExprNode expr1(){
        ExprNode expr = exprn();
        while( LA(1) == TokenType.MUL || LA(1) == TokenType.DIV){
            TokenType op = this.LA(1);
            match(op); //消耗掉
            expr = new BinopExpr(op,expr, expr2());
        }
        return  expr;
    }


    // expr2 ::= expr3 { ^  expr3 }
    private ExprNode expr2(){
        ExprNode expr = expr3();
        while(LA(1) == TokenType.POWER){
            TokenType op = this.LA(1);
            match(op); //消耗掉
            expr = new BinopExpr(op,expr, expr2());
        }
        return  expr;
    }


    private ExprNode expr3(){
        if(this.LA(1) == TokenType.SUB){
            match(TokenType.SUB);
            return  new UnopExpr(TokenType.UNMINUS,exprn());
        }else {
            ExprNode expr = exprn();
            return  expr;

        }

    }
//     exp ::=  nil | false | true | Numeral | LiteralString | ‘...’ | functiondef |
//        	 prefixexp | tableconstructor
//    functiondef ::= function funcbody
//    tableconstructor ::= ‘{’ [fieldlist] ‘}’

    private ExprNode exprn(){
        if(this.LA(1) == TokenType.NIL){
            match(TokenType.NIL);
            return new NilExp();
        }else if(this.LA(1) == TokenType.TRUE){
            match(TokenType.TRUE);
            return new TrueExp();
        }else if(this.LA(1) == TokenType.FALSE){
            match(TokenType.FALSE);
            return new FalseExp();
        }else if(this.LA(1) == TokenType.NUMBER){
            String str = LT(1).text;
            match(TokenType.NUMBER);
            return new NumExpr(str);
        } else if(this.LA(1) ==TokenType.STRING){
            String str = LT(1).text;
            match(TokenType.STRING);
            return new StringExpr(str);
        } else if(this.LA(1) ==TokenType.VARARG){
            String str = LT(1).text;
            match(TokenType.VARARG);
            return new VarargExpr(str);
        } else if(this.LA(1) ==TokenType.FUNCTION){
            return  funcDefExpr();
        } else if(this.LA(1) ==TokenType.LBRACE){
            return  tableExpr();
        }else {
            return prefixExpr();
        }

    }

    private ExprNode tableExpr(){
        return null;
    }

    public  FuncDefExpr funcDefExpr(){
        match(TokenType.FUNCTION);
        FuncDefExpr funcDefExpr = new FuncDefExpr();
        funcDefExpr.funcBody =  funcbody();
        return  funcDefExpr;
    }

    /**
     * 	funcbody ::= ‘(’ [parlist] ‘)’ block end
     * 	parlist ::= namelist [‘,’ ‘...’] | ‘...’
     *	namelist ::= Name {‘,’ Name}
     *
     *  ------------
     *  parlist ::= Name {‘,’ Name} [‘,’ ‘...’] | ‘...’
     *
     * @param
     */
    private   FuncBody funcbody(){
        match(TokenType.LPAREN);
        FuncBody funcBody = new FuncBody();
        if(LA(1) != TokenType.RPAREN){
            if(LA(1) == TokenType.VARARG){
                match(TokenType.VARARG);
                funcBody.varargExpr = new VarargExpr();
            }else {
                List<String> args = new ArrayList<>();
                funcBody.params = args;
                args.add(LT(1).text);
                match(TokenType.ID);
                while(LA(1) == TokenType.COMMA){
                    match(TokenType.COMMA);
                    if(LA(1) == TokenType.ID){
                        args.add(LT(1).text);
                        match(TokenType.ID);
                    }else if(LA(1) == TokenType.VARARG){ //只能出现在最后
                        match(TokenType.VARARG);
                        funcBody.varargExpr = new VarargExpr();
                        break;
                    }else {
                        throw new Error("expect name or vararg,but found"+ LT(1));
                    }
                }
            }
        }
        match(TokenType.RPAREN);
        Block block = block();
        funcBody.block = block;
        match(TokenType.END);
        return funcBody;
    }


    public FuncCallExpr funcCallExpr(){
        String name = LT(1).text;
        match(TokenType.ID);
        match(TokenType.LPAREN);
        List<ExprNode> args = new ArrayList<>();
        while (LA(1) != TokenType.RPAREN){
            ExprNode expr = expr();
            args.add(expr);
            if(LA(1) == TokenType.COMMA){
                match(TokenType.COMMA);
            }
        }
        match(TokenType.RPAREN);
        return new FuncCallExpr(name,args);
    }

    public boolean isBlockEnd(){
        if(this.LA(1) ==TokenType.EOF
                || this.LA(1) == TokenType.END
                || this.LA(1) == TokenType.ELSEIF
                || this.LA(1) == TokenType.UNTIL
                || this.LA(1) == TokenType.ELSE){
            return true;
        }
        return false;
    }


    public ExprNode exprPrecedenceClimbing(){

        return exprPrecedenceClimbing(expr3(), 0);
    }

    public ExprNode exprPrecedenceClimbing(ExprNode lhs, int min_precedence ){
        TokenType lookahead = LA(1);
        while(( Token.BINOPS.containsKey(lookahead) ) &&
                Token.BINOPS.get(lookahead)  >= min_precedence ){
            TokenType op = lookahead;
            match(op);   // advance to next token
            ExprNode rhs =  expr3();
            lookahead =  LA(1);
            while(( Token.BINOPS.containsKey(lookahead)  && Token.BINOPS.get(lookahead)  > Token.BINOPS.get(op) )
                    ||
                    ( Token.BINOP_RIGHT_ASSOC.contains(lookahead) && Token.BINOPS.get(lookahead) == Token.BINOPS.get(op))

            ){
                rhs = exprPrecedenceClimbing(rhs, Token.BINOPS.get(lookahead));
                lookahead = LA(1);  //peek next token
            }
            lhs = new BinopExpr(op,lhs, rhs);
        }
        return lhs;
    }


    /***
     *
     * 	prefixexp ::= var | functioncall | ‘(’ exp ‘)’
     *	var ::=  Name | prefixexp ‘[’ exp ‘]’ | prefixexp ‘.’ Name
     *	functioncall ::=  prefixexp args | prefixexp ‘:’ Name args
     *	args ::=  ‘(’ [explist] ‘)’ | tableconstructor | LiteralString
     *
     *----------------------------
     *
     *    prefixexp ::=  Name | prefixexp ‘[’ exp ‘]’ | prefixexp ‘.’ Name
     *                  |  prefixexp args | prefixexp ‘:’ Name args
     *                  | ‘(’ exp ‘)’
     *
     *-------------------------
     *
     *    prefixexp ::=  Name  pp | ‘(’ exp ‘)’ pp
     *
     *    pp        ::=   ‘[’ exp ‘]’ pp |  ‘.’ Name  pp |   args  pp |  ‘:’ Name args  pp | 空
     *
     *
     *    pp        ::=   ‘[’ exp ‘]’ pp
     *                   |  ‘.’ Name  pp
     *                   |   args  pp  === ‘(’ [explist] ‘)’ | tableconstructor | LiteralString
     *                   |  ‘:’ Name args  pp
     *                   | 空
     *
     */

    private ExprNode prefixExpr(){
        switch (this.LA(1)){
            case ID:
                return namePrefixExpr();
            case LPAREN:
                return parenPrefixExpr();
        }
        throw new Error(""+ LT(1));
    }
   /**
    *            prefixexp ::=  Name  pp | ‘(’ exp ‘)’ pp
     *       pp        ::=   ‘[’ exp ‘]’ pp |  ‘.’ Name  pp |   args  pp |  ‘:’ Name args  pp | 空
     *
             *
             *    pp        ::=   ‘[’ exp ‘]’ pp
     *                   |  ‘.’ Name  pp
     *                   |   args  pp  === ‘(’ [explist] ‘)’ | tableconstructor | LiteralString
     *                   |  ‘:’ Name args  pp
     *                   | 空
     *
             */

    private PrefixExpr.NamePrefixExpr namePrefixExpr(){
        String name = LT(1).text;
        match(TokenType.ID);
        PrefixExpr.PrefixSuffixExpr expr = prefixExprSuffix();
        return new PrefixExpr.NamePrefixExpr(name, expr);
    }
//     *            prefixexp ::=  Name  pp | ‘(’ exp ‘)’ pp
    private PrefixExpr.ParenPrefixExpr parenPrefixExpr(){
        match(TokenType.LPAREN);
        ExprNode exprNode = expr();
        match(TokenType.RPAREN);
        PrefixExpr.PrefixSuffixExpr expr = prefixExprSuffix();
        return new PrefixExpr.ParenPrefixExpr(exprNode, expr);
    }

    /**
              *    pp ::=‘[’ exp ‘]’ pp
     *                   |  ‘.’ Name  pp
     *                   |   args  pp  === ‘(’ [explist] ‘)’ | tableconstructor | LiteralString
     *                   |  ‘:’ Name args  pp
     *                   | 空
     *
             */

    private PrefixExpr.PrefixSuffixExpr prefixExprSuffix(){
        switch (this.LA(1)){
            case LBRACK:
                match(TokenType.LBRACK);
                ExprNode exprNode = expr();
                match(TokenType.RBRACK);
                return new PrefixExpr.PrefixSuffixExpr(TokenType.LBRACK,exprNode, prefixExprSuffix());
            case DOT:
                match(TokenType.DOT);
                String name = LT(1).text;
                match(TokenType.ID);
                return new PrefixExpr.PrefixSuffixExpr(TokenType.DOT, name, prefixExprSuffix());
            case COLON:
                match(TokenType.COLON);
                name = LT(1).text;
                match(TokenType.ID);
                FuncArgs funcArgs =funcArgs();
                return new PrefixExpr.PrefixSuffixExpr(TokenType.COLON, name,funcArgs, prefixExprSuffix());
            case LPAREN:
                 funcArgs =funcArgs();
                return new PrefixExpr.PrefixSuffixExpr(TokenType.FUNCTION, funcArgs, prefixExprSuffix());
        }
        return null;
    }

    private  FuncArgs funcArgs(){
        match(TokenType.LPAREN);
        List<ExprNode> args = new ArrayList<>();
        while (LA(1) != TokenType.RPAREN){
            ExprNode expr = expr();
            args.add(expr);
            if(LA(1) == TokenType.COMMA){
                match(TokenType.COMMA);
            }
        }
        match(TokenType.RPAREN);
        return new FuncArgs(args);
    }

}
