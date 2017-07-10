import com.encryptlang.v1.Parser;
import com.encryptlang.v1.Token;
import com.encryptlang.v1.lex.Lexer;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class TestLexer {

    @Test
    public void testLexer () throws IOException {
        String exp = "request.json = \"h\" +\"xdsafds\"\n";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        while (true){

            Token token = parser.nextToken();
            System.out.print(token);
            if (token.type == Token.Type.EndSymbol){
                break;
            }
        }
    }



    @Test
    public void testFunctionCall () throws IOException {
        String exp = "var helo = abc()\n" + "var twoparam = bcd(a, \"ccc\")\n" + "var oneparem =xxx(a)\n" + "var threeparam = tp(a, b, c)";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        while (true){

            Token token = parser.nextToken();
            System.out.print(token);
            if (token.type == Token.Type.EndSymbol){
                break;
            }
        }
    }
}
