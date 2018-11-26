import com.encryptlang.lua.LuaLexer;
import com.encryptlang.lua.Token;
import com.encryptlang.lua.TokenType;
import org.junit.Test;

public class LuaLexerTest {

    @Test
    public  void testLuaLexer(){
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
        Token token ;
        do {
            token = jsonLexer.nextToken();
            System.out.println(token);
        }while (token.type != TokenType.EOF);

    }


    @Test
    public  void testLuaLexer2(){
        String input = "return require('./init')(function (...)\n" +
                "\n" +
                "  local luvi = require('luvi')\n" +
                "  local uv = require('uv')\n" +
                "  local utils = require('utils')\n" +
                "  local package = require('./package.lua')\n" +
                "\n" +
                "  local startRepl = nil\n" +
                "  local combo = nil\n" +
                "  local script = nil\n" +
                "  local extra = {}\n" +
                "\n" +
                "  local function usage()\n" +
                "    print(\"Usage: \" .. args[0] .. \" [options] script.lua [arguments]\"..[[\n" +
                "  Options:\n" +
                "    -h, --help          Print this help screen.\n" +
                "    -v, --version       Print the version.\n" +
                "    -e code_chunk       Evaluate code chunk and print result.\n" +
                "    -i, --interactive   Enter interactive repl after executing script.\n" +
                "    -n, --no-color      Disable colors.\n" +
                "    -c, --16-colors     Use simple ANSI colors\n" +
                "    -C, --256-colors    Use 256-mode ANSI colors\n" +
                "                        (Note, if no script is provided, a repl is run instead.)\n" +
                "  ]])\n" +
                "    startRepl = false\n" +
                "  end\n" +
                "\n" +
                "  local function version()\n" +
                "    print('luvit version: ' .. package.version)\n" +
                "    print('luvi version: ' .. require('luvi').version)\n" +
                "    for k, v in pairs(require('luvi').options) do\n" +
                "      print(k .. ' version: ' .. tostring(v))\n" +
                "    end\n" +
                "    startRepl = false\n" +
                "  end\n" +
                "\n" +
                "\n" +
                "  local shorts = {\n" +
                "    h = \"help\",\n" +
                "    v = \"version\",\n" +
                "    e = \"eval\",\n" +
                "    i = \"interactive\",\n" +
                "    n = \"no-color\",\n" +
                "    c = \"16-colors\",\n" +
                "    C = \"256-colors\",\n" +
                "  }\n" +
                "\n" +
                "  local flags = {\n" +
                "    help = usage,\n" +
                "    version = version,\n" +
                "    eval = function ()\n" +
                "      local repl = require('repl')(utils.stdin, utils.stdout)\n" +
                "      combo = repl.evaluateLine\n" +
                "      startRepl = false\n" +
                "    end,\n" +
                "    interactive = function ()\n" +
                "      startRepl = true\n" +
                "    end,\n" +
                "    [\"no-color\"] = function ()\n" +
                "      utils.loadColors(false)\n" +
                "    end,\n" +
                "    [\"16-colors\"] = function ()\n" +
                "      utils.loadColors(16)\n" +
                "    end,\n" +
                "    [\"256-colors\"] = function ()\n" +
                "      utils.loadColors(256)\n" +
                "    end,\n" +
                "  }\n" +
                "\n" +
                "  for i = 1, #args do\n" +
                "    local arg = args[i]\n" +
                "    if script then\n" +
                "      extra[#extra + 1] = arg\n" +
                "    elseif combo then\n" +
                "      combo(arg)\n" +
                "      combo = nil\n" +
                "    elseif string.sub(arg, 1, 1) == \"-\" then\n" +
                "      local flag\n" +
                "      if (string.sub(arg, 2, 2) == \"-\") then\n" +
                "        flag = string.sub(arg, 3)\n" +
                "      else\n" +
                "        arg = string.sub(arg, 2)\n" +
                "        flag = shorts[arg] or arg\n" +
                "      end\n" +
                "      local fn = flags[flag] or usage\n" +
                "      fn()\n" +
                "    else\n" +
                "      script = arg\n" +
                "    end\n" +
                "  end\n" +
                "\n" +
                "  if combo then error(\"Missing flag value\") end\n" +
                "\n" +
                "  if startRepl == nil and not script then startRepl = true end\n" +
                "\n" +
                "  if script then\n" +
                "    require(luvi.path.join(uv.cwd(), script))\n" +
                "  end\n" +
                "\n" +
                "  if startRepl then\n" +
                "    local env = require('env')\n" +
                "    local pathJoin = require('luvi').path.join\n" +
                "    local fs = require('fs')\n" +
                "    local c = utils.color\n" +
                "    local greeting = \"Welcome to the \" .. c(\"err\") .. \"L\" .. c(\"quotes\") .. \"uv\" .. c(\"table\") .. \"it\" .. c() .. \" repl!\"\n" +
                "    local historyFile\n" +
                "    if require('ffi').os == \"Windows\" then\n" +
                "      historyFile = pathJoin(env.get(\"APPDATA\"), \"luvit_history\")\n" +
                "    else\n" +
                "      historyFile = pathJoin(env.get(\"HOME\"), \".luvit_history\")\n" +
                "    end\n" +
                "    local lines = fs.readFileSync(historyFile) or \"\"\n" +
                "    local function saveHistory(lines)\n" +
                "      fs.writeFileSync(historyFile, lines)\n" +
                "    end\n" +
                "    require('repl')(utils.stdin, utils.stdout, greeting, ...).start(lines, saveHistory)\n" +
                "\n" +
                "  end\n" +
                "end, ...)";

        LuaLexer jsonLexer = new LuaLexer(input);
        Token token ;
        do {
            token = jsonLexer.nextToken();
            System.out.println(token);
        }while (token.type != TokenType.EOF);

    }

}
