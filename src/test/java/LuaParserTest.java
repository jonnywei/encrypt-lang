import com.encryptlang.lua.LuaLexer;
import com.encryptlang.lua.LuaParser;
import com.encryptlang.lua.ast.Block;
import org.junit.Test;

public class LuaParserTest {


    @Test
    public void testLuaDoEnd(){
        String input = "do ; end ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block = parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLua(){
        String input = "do ; end ; do do do ; end end end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLuaWhile(){
        String input = "do  while true do ; end  end; ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaWhileDo(){
        String input = "  while true do  do ; end end  end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testLuaIf(){
        String input = "  if true then do ; end else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLuaIfElseIf(){
        String input = "  if true then do ; end elseif false then do ;end  else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaIfElseRepeat(){
        String input = "  if true then  repeat ; until false  else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testLuaFuncCall(){
        String input = "  if true then  func(true,false)  else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testLuaFuncNoArg(){
        String input = "  if true then  func()  else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaFuncStringArg(){
        String input = "  if true then  func('abc' )  else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaFuncFuncArg(){
        String input = "  if true then  func('abc',da() )  else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }
}
