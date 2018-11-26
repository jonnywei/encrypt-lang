import com.encryptlang.json.JsonLexer;
import com.encryptlang.json.JsonParser;
import com.encryptlang.json.ParseTreeJsonParser;
import org.junit.Test;

public class TestParseTreeJsonParser {


    @Test
    public void testJosn(){
        String input = "{\"title\":444,\"json.url\": 23323434,\"keywords\":\"json在线解析\",\"功能\":[\"JSON美化\",\"JSON数据类型显示\",\"JSON数组显示角标\",\"高亮显示\",\"错误提示\",{\"备注\":[\"www.sojson.com\",\"json.la\"]}],\"加入我们\":{\"qq群\":\"259217951\"}}";

        JsonLexer jsonLexer = new JsonLexer(input);
        ParseTreeJsonParser parser = new ParseTreeJsonParser(jsonLexer);
        ParseTreeJsonParser.ParseTree parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

    }


    @Test
    public void testJson(){
        String input = "{'aaa': 444,'dddd'}";

        JsonLexer jsonLexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(jsonLexer);
        parser.json();

    }

    @Test
    public void testJsonArray(){
        String input = "['aaa',444]";

        JsonLexer jsonLexer = new JsonLexer(input);
        ParseTreeJsonParser parser = new ParseTreeJsonParser(jsonLexer);
        ParseTreeJsonParser.ParseTree parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

    }


    @Test
    public void testJsonTrue(){
        String input = "['aaa',true,null]";

        JsonLexer jsonLexer = new JsonLexer(input);
        ParseTreeJsonParser parser = new ParseTreeJsonParser(jsonLexer);
        ParseTreeJsonParser.ParseTree parseTree =  parser.parse();

        System.out.println(parseTree.toStringTree());

    }
}
