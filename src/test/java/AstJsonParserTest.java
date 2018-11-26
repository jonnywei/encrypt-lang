import com.encryptlang.json.AstJsonParser;
import com.encryptlang.json.JsonLexer;
import org.junit.Test;

public class AstJsonParserTest {


    @Test
    public void testJosn(){
        String input = "{\"title\":444,\"json.url\": 23323434,\"keywords\":\"json在线解析\",\"功能\":[\"JSON美化\",\"JSON数据类型显示\",\"JSON数组显示角标\",\"高亮显示\",\"错误提示\",{\"备注\":[\"www.sojson.com\",\"json.la\"]}],\"加入我们\":{\"qq群\":\"259217951\"}}";

        JsonLexer jsonLexer = new JsonLexer(input);
        AstJsonParser parser = new AstJsonParser(jsonLexer);
        AstJsonParser.AST parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

    }


    @Test
    public void testJson(){
        String input = "{'aaa': 444,'dddd':true}";

        JsonLexer jsonLexer = new JsonLexer(input);
        AstJsonParser parser = new AstJsonParser(jsonLexer);
        AstJsonParser.AST parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

    }

    @Test
    public void testJsonArray(){
        String input = "['aaa',444,null]";
        JsonLexer jsonLexer = new JsonLexer(input);
        AstJsonParser parser = new AstJsonParser(jsonLexer);
        AstJsonParser.AST parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

    }


    @Test
    public void testJsonTrue(){
        String input = "{'abc':['aaa',true,null],'d':222}";

        JsonLexer jsonLexer = new JsonLexer(input);
        AstJsonParser parser = new AstJsonParser(jsonLexer);
        AstJsonParser.AST ast =  parser.parse();

        System.out.println(ast.toStringTree());
        System.out.println(ast.eval());

    }

    @Test
    public void testJsonNest(){
        String input = "{'abc':['aaa',true,null,{'inner':'iva'}],'d':222}";

        JsonLexer jsonLexer = new JsonLexer(input);
        AstJsonParser parser = new AstJsonParser(jsonLexer);
        AstJsonParser.AST ast =  parser.parse();

        System.out.println(ast.toStringTree());
        Object o = ast.eval();

        System.out.println(o);

    }
}
