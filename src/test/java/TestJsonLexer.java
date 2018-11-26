import com.encryptlang.json.JsonLexer;
import com.encryptlang.json.Token;
import org.junit.Test;

import static com.encryptlang.json.Lexer.EOF_TYPE;

public class TestJsonLexer {


    @Test
    public void testJosn(){
        String input = "{\"title\":DDDD,\"json.url\": 23323434,\"keywords\":\"json在线解析\",\"功能\":[\"JSON美化\",\"JSON数据类型显示\",\"JSON数组显示角标\",\"高亮显示\",\"错误提示\",{\"备注\":[\"www.sojson.com\",\"json.la\"]}],\"加入我们\":{\"qq群\":\"259217951\"}}";

        JsonLexer jsonLexer = new JsonLexer(input);
        Token token ;
        do {
             token = jsonLexer.nextToken();
            System.out.println(token);
        }while (token.type != EOF_TYPE);

    }


    @Test
    public void testJson(){
        String input = "{\"title\":DDDD, \n \"json.url\" 31243214.,\"keywords\":\"json在线解析\",\"功能\":[\"JSON美化\",\"JSON数据类型显示\",\"JSON数组显示角标\",\"高亮显示\",\"错误提示\",{\"备注\":[\"www.sojson.com\",\"json.la\"]}],\"加入我们\":{\"qq群\":\"259217951\"}}";

        JsonLexer jsonLexer = new JsonLexer(input);
        Token token ;
        do {
            token = jsonLexer.nextToken();
            System.out.println(token);
        }while (token.type != EOF_TYPE);

    }
    @Test
    public void testNumber(){
        String input = "ddd 32432 34324.111 31243214. 31431 3333.1";

        JsonLexer jsonLexer = new JsonLexer(input);
        Token token ;
        do {
            token = jsonLexer.nextToken();
            System.out.println(token);
        }while (token.type != EOF_TYPE);

    }

    @Test
    public void testNumber2(){
        String input = "dafdsfdsf dsfds44 44 4254 454 42 456 3";

        JsonLexer jsonLexer = new JsonLexer(input);
        Token token ;
        do {
            token = jsonLexer.nextToken();
            System.out.println(token);
        }while (token.type != EOF_TYPE);

    }
}
