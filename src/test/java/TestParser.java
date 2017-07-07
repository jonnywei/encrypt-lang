import com.encryptlang.v1.Parser;
import com.encryptlang.v1.Token;
import com.encryptlang.v1.env.BaseEnvironment;
import com.encryptlang.v1.env.Environment;
import com.encryptlang.v1.lex.Lexer;
import com.encryptlang.v1.node.ExpressionBlockNode;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by jianjunwei on 2017/7/7.
 */
public class TestParser {



    @Test
    public void testParser () throws IOException {
        String exp = "\"vcadf\" + sdafd(\"xxx\") +\"def\" ";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);



        ExpressionBlockNode node = parser.parse();
        System.out.print(node);
    }



    @Test
    public void testParserThreeVariableAdd () throws IOException {
        String exp = "request.json = \"a\" +\"b\" +\"c\"\n";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);



        ExpressionBlockNode node = parser.parse();
        System.out.print(node);
    }


    @Test
    public void testParserThreeVarAndFunctionAdd () throws IOException {
        String exp = "request.json = \"a\" + change(\"me\") +\"c\"\n";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);



        ExpressionBlockNode node = parser.parse();
        System.out.print(node);
    }




    @Test
    public void testParserEval () throws IOException {
        String exp = "\n" + "var same = \"same str\"\n" + "\n" + "var change = \"change it\"\n" + "\n" + "request.json = DES( AES(same)) ";

        Reader reader = new StringReader(exp);

        Lexer lexer = new Lexer(reader);

        Parser parser = new Parser(lexer);

        Environment environment = BaseEnvironment.getBaseEnvironment();
        ExpressionBlockNode node = parser.parse();
        System.out.print(node.eval(environment));
    }
}
