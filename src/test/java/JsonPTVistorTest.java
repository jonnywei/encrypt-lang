import com.encryptlang.json.JsonLexer;
import com.encryptlang.json.JsonPTVistor;
import com.encryptlang.json.ParseTreeJsonParser;
import org.junit.Test;

public class JsonPTVistorTest {


    @Test
    public void testJsonArray(){
        String input = "['aaa',444,'ddd']";

        JsonLexer jsonLexer = new JsonLexer(input);
        ParseTreeJsonParser parser = new ParseTreeJsonParser(jsonLexer);
        ParseTreeJsonParser.ParseTree parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

        JsonPTVistor vistor = new JsonPTVistor();

        System.out.println(vistor.visit(parseTree));


    }
    @Test
    public void testJsonNestArray(){
        String input = "['aaa',444,'ddd',[333,3432,true,false,null]]";

        JsonLexer jsonLexer = new JsonLexer(input);
        ParseTreeJsonParser parser = new ParseTreeJsonParser(jsonLexer);
        ParseTreeJsonParser.ParseTree parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

        JsonPTVistor vistor = new JsonPTVistor();

        System.out.println(vistor.visit(parseTree));


    }
}
