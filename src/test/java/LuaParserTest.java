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
        String input = "  if true then  ABC = fund()  ddd() else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testLuaFor(){
        String input = "  if true then  ABC = fund()  ddd() else ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaForInit(){
        String input = " for abc ='daf' , true do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLuaForInit2(){
        String input = " for abc ='daf' , true,'DDDD' do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLuaForIn(){
        String input = " for ADD,DAF in 'ddddd' do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaExpr(){
        String input = " while 222+223333+343*343  do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLuaExprPower(){
        String input = " while 222+223333+343*343^2/3-2  do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testLuaExprPowerAss(){
        String input = " while 222+343*343^2^33/-3-2  do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }
}
