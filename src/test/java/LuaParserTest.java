import com.encryptlang.lua.LuaLexer;
import com.encryptlang.lua.LuaParser;
import com.encryptlang.lua.ast.Block;
import com.encryptlang.lua.tree.PrintVisitor;
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
        String input = "  --ddd \nif true then  repeat ; until false  else ; end";
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


    @Test
    public void testLuaExprVararg(){
        String input = " while...  do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaExprNil(){
        String input = " while nil  do ddd = dafd() end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaExprFuncDef(){
        String input = " while function (a , b, ...) ; end  do ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaStatLocalFunc(){
        String input = "  function addd.ddd (a,b) ; end";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testLuaStatLocalDecl(){
        String input = " local dddd,dafd = 3333, 222*222";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaStatLocalDeclFunExpr(){
        String input = " local dddd,fn = 3333, abd( '',2,3)";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLuaStatGotoLabel(){
        String input = " goto abc break  :: eee :: ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testPrefixExpr(){
        String input = " local dd = dafd[ddd].adf[dddd].aa";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }




    @Test
    public void testPrefixExprFuncall(){
        String input = " local dd = dafd[ddd].adf[dddd].aa()";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testAssignStat(){
        String input = " abvd,ddd[333],fff.addd[33] = 2222,33223";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testLogicalOperatorStat(){
        String input = "if ddd and 2222 or dddd  then ; end ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testLogicalOperatorNot(){
        String input = "a =  ddd and 2222 or not dddd  ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public void testPowerAndNot(){
        String input = "a =  #ddd and 2222  +  not dddd ^ 333 /4 ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testPowerAndNotWithParens(){
        String input = "a =  #ddd and ( 2222  +  not dddd )^ 333 /4 ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }


    @Test
    public void testTableEmpty(){
        String input = "a =  {} ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testTable(){
        String input = "a =  { k= 2222; x ;  2222*222,} ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }

    @Test
    public void testFunctionString(){
        String input = "bb =  aaa.dddd ()";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());
    }



    @Test
    public  void testLuaBigFile(){
        String input = "local content_length=tonumber(ngx.req.get_headers()['content-length'])\n" +
                "local method=ngx.req.get_method()\n" +
                "local ngxmatch=ngx.re.match\n" +
                "if whiteip() then\n" +
                "elseif blockip() then\n" +
                "elseif denycc() then\n" +
                "elseif ngx.var.http_Acunetix_Aspect then\n" +
                "    ngx.exit(444)\n" +
                "elseif ngx.var.http_X_Scan_Memo then\n" +
                "    ngx.exit(444)\n" +
                "elseif whiteurl() then\n" +
                "elseif ua() then\n" +
                "elseif url() then\n" +
                "elseif args() then\n" +
                "elseif cookie() then\n" +
                "elseif PostCheck then\n" +
                "    if method==\"POST\" then   \n" +
                "            local boundary = get_boundary()\n" +
                "\t    if boundary then\n" +
                "\t    local len = string.len\n" +
                "            local sock, err = ngx.req.socket()\n" +
                "    \t    if not sock then\n" +
                "\t\t\t\t\treturn\n" +
                "            end\n" +
                "\t    ngx.req.init_body(128 * 1024)\n" +
                "            sock:settimeout(0)\n" +
                "\t    local content_length = nil\n" +
                "    \t    content_length=tonumber(ngx.req.get_headers()['content-length'])\n" +
                "    \t    local chunk_size = 4096\n" +
                "            if content_length < chunk_size then\n" +
                "\t\t\t\t\tchunk_size = content_length\n" +
                "\t    end\n" +
                "            local size = 0\n" +
                "\t    while size < content_length do\n" +
                "\t\tlocal data, err, partial = sock:receive(chunk_size)\n" +
                "\t\tdata = data or partial\n" +
                "\t\tif not data then\n" +
                "\t\t\treturn\n" +
                "\t\tend\n" +
                "\t\tngx.req.append_body(data)\n" +
                "        \tif body(data) then\n" +
                "\t   \t        return true\n" +
                "    \t    \tend\n" +
                "\t\tsize = size + len(data)\n" +
                "\t\tlocal m = ngxmatch(data,[[Content-Disposition: form-data;(.+)filename=\"(.+)\\\\.(.*)\"]],'ijo')\n" +
                "        \tif m then\n" +
                "            \t\tfileExtCheck(m[3])\n" +
                "            \t\tfiletranslate = true\n" +
                "        \telse\n" +
                "            \t\tif ngxmatch(data,\"Content-Disposition:\",'isjo') then\n" +
                "                \t\tfiletranslate = false\n" +
                "            \t\tend\n" +
                "            \t\tif filetranslate==false then\n" +
                "            \t\t\tif body(data) then\n" +
                "                    \t\t\treturn true\n" +
                "                \t\tend\n" +
                "            \t\tend\n" +
                "        \tend\n" +
                "\t\tlocal less = content_length - size\n" +
                "\t\tif less < chunk_size then\n" +
                "\t\t\tchunk_size = less\n" +
                "\t\tend\n" +
                "\t end\n" +
                "\t ngx.req.finish_body()\n" +
                "    else\n" +
                "\t\t\tngx.req.read_body()\n" +
                "\t\t\tlocal args = ngx.req.get_post_args()\n" +
                "\t\t\tif not args then\n" +
                "\t\t\t\treturn\n" +
                "\t\t\tend\n" +
                "\t\t\tfor key, val in pairs(args) do\n" +
                "\t\t\t\tif type(val) == \"table\" then\n" +
                "\t\t\t\t\tif type(val[1]) == \"boolean\" then\n" +
                "\t\t\t\t\t\treturn\n" +
                "\t\t\t\t\tend\n" +
                "\t\t\t\t\tdata=table.concat(val, \", \")\n" +
                "\t\t\t\telse\n" +
                "\t\t\t\t\tdata=val\n" +
                "\t\t\t\tend\n" +
                "\t\t\t\tif data and type(data) ~= \"boolean\" and body(data) then\n" +
                "                \t\t\tbody(key)\n" +
                "\t\t\t\tend\n" +
                "\t\t\tend\n" +
                "\t\tend\n" +
                "    end\n" +
                "else\n" +
                "    return\n" +
                "end";

        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());

    }



    @Test
    public  void testLuaPrefix(){
        String input = "local m = ngxmatch(data,[[Content-Disposition: form-data;(.+)filename=\\\"(.+)\\\\\\\\.(.*)\\\"]],'ijo')";

        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());

    }


    @Test
    public  void testLuaPrefix1(){
        String input = "local m = ngxmatch(data,[[Content-Disposition: form-data;(.+)filename=\\\"(.+)\\\\\\\\.(.*)\\\"]],'ijo')";
//        String aa = "sss".substring() ;
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        System.out.println(block.toStringTree());

    }


    @Test
    public void testLuaIf1(){
        String input = "  if true then do ; end else ; end enda =  { k= 2222; x ;  2222*222,}  ";
        LuaLexer jsonLexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(jsonLexer);
        Block block =  parser.block();
        PrintVisitor printVistor = new PrintVisitor();
        printVistor.visit(block)   ;
    }
}
