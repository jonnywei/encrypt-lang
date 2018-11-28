package com.encryptlang.lua;

import com.encryptlang.lua.ast.*;
import com.encryptlang.lua.ast.expr.BinopExpr;
import com.encryptlang.lua.ast.expr.FuncCallExpr;
import com.encryptlang.lua.ast.expr.NumExpr;
import com.encryptlang.lua.ast.expr.StringExpr;

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
    public StatNode statement(){
        switch (LA(1)){
            case DO:           return doStatement();
            case SEMI:         return emptyStatement();
            case WHILE:        return whileStatement();
            case IF:           return ifStatement();
            case REPEAT:       return repeatStatement();
            case FOR:          return  forStatement();
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



    public FuncCallStat funcCallStatement(){
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
        return new FuncCallStat(name,args);
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
     * @return
     */

    public ExprNode expr(){
        return expr0();
    }
     // expr0 ::= exprn { binop exprn }
    private ExprNode expr0(){
        ExprNode expr = exprn();
        while(LA(1) == TokenType.ADD ||LA(1) == TokenType.SUB
                || LA(1) == TokenType.MUL || LA(1) == TokenType.DIV
                || LA(1) == TokenType.POWER){
            TokenType op = this.LA(1);
            match(op); //消耗掉
            expr = new BinopExpr(op,expr, exprn());
        }
        return  expr;
    }

    private ExprNode exprn(){
        if(this.LA(1) == TokenType.TRUE){
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
        }else if(this.LA(1) ==TokenType.ID){ //这里有多种情况，可能赋值或者函数调用
            if(this.LA(2) == TokenType.ASSIGN ){
                return  funcCallExpr();
            }else if (this.LA(2) == TokenType.LPAREN ){
                return  funcCallExpr();
            }
        }
        throw new Error(""+ LT(1));
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


}
