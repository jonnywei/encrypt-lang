package com.encryptlang.v0;

import com.encryptlang.v0.Reader;
import com.encryptlang.v0.node.Token;

import java.io.IOException;
import java.util.List;

/**
 * Created by jianjunwei on 2017/7/5.
 */
public class REPL {

    public static void main(String[] args) throws IOException {
        String exp = "var heelo = safdsaf(dasfdsa)";

        List<Token> tokens = new Reader().read(exp);

        for(Token t: tokens){
            System.out.println(t);
        }
    }
}
