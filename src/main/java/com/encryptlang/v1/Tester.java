package com.encryptlang.v1;

import com.encryptlang.v1.lex.Lexer;
import com.encryptlang.v1.node.ExpressionBlockNode;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        String exp = "var heelo = safdsaf(dasfdsa)\n" + "badafd.xxx = sdfds ( xcafds(xxxx))\n" + "request.json = \"hello\"\n"
                     + "request.json = \"hello\" +\"xdsafds\"";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);

//
//        while (true){
//
//            Token token = parser.nextToken();
//            System.out.print(token);
//            if (token.type == Token.Type.EndSymbol){
//                break;
//            }
//        }

        ExpressionBlockNode node = parser.parse();
        System.out.print(node);

        System.out.println();

    }
}
