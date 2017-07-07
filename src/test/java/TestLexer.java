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
}
