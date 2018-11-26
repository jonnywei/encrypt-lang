package com.encryptlang.lua;

import com.encryptlang.lua.ast.*;
import com.encryptlang.lua.ast.expr.StringExpr;

import java.util.ArrayList;
import java.util.List;

public class LuaParser extends Parser {

    public LuaParser(LuaLexer lexer) {
        super(lexer);
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
        switch (lookahead.type){
            case DO:           return doStatement();
            case SEMI:         return emptyStatement();
            case WHILE:        return whileStatement();
            case IF:           return  ifStatement();
            case REPEAT:       return  repeatStatement();
            case ID:           return  funcCallStatement();
        }
        throw new Error(""+lookahead);
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
        while(lookahead.type == TokenType.ELSEIF){
            match(TokenType.ELSEIF);
            ExprNode elseExpr = expr();
            match(TokenType.THEN);
            Block blockExpr = block();
            IfStat.ElseIfStat elseIfStat = new IfStat.ElseIfStat(elseExpr, blockExpr);
            elseIfStats.add(elseIfStat);
        }
        ifStat.elseIfs = elseIfStats;
        if(lookahead.type == TokenType.ELSE){
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
        String name = lookahead.text;
        match(TokenType.ID);
        match(TokenType.LPAREN);
        List<ExprNode> args = new ArrayList<>();
        while (lookahead.type != TokenType.RPAREN){
            ExprNode expr = expr();
            args.add(expr);
            if(lookahead.type == TokenType.COMMA){
                match(TokenType.COMMA);
            }
        }
        match(TokenType.RPAREN);
        return new FuncCallStat(name,args);
    }


    public ExprNode expr(){
        if(this.lookahead.type == TokenType.TRUE){
            match(TokenType.TRUE);
            return new TrueExp();
        }else if(this.lookahead.type == TokenType.FALSE){
            match(TokenType.FALSE);
            return new FalseExp();
        }else if(this.lookahead.type ==TokenType.STRING){
            String str = lookahead.text;
            match(TokenType.STRING);
            return new StringExpr(str);
        }else if(this.lookahead.type ==TokenType.ID){ //这里有多种情况，可能赋值或者函数调用
            String str = lookahead.text;
            match(TokenType.STRING);
            return new StringExpr(str);
        }
        throw new Error(""+lookahead);
    }


    public boolean isBlockEnd(){
        if(this.lookahead.type ==TokenType.EOF
                || this.lookahead.type == TokenType.END
                || this.lookahead.type == TokenType.ELSEIF
                || this.lookahead.type == TokenType.UNTIL
                || this.lookahead.type == TokenType.ELSE){
            return true;
        }
        return false;
    }


}
