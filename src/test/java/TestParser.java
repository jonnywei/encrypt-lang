import com.encryptlang.v1.Parser;
import com.encryptlang.v1.Token;
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
}
