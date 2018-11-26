package com.encryptlang.json;

import java.util.*;

public class JsonPTVistor {

    public Object visit(ParseTreeJsonParser.ParseTree parseTree){
        Token token = parseTree.getToken();
        String rule = parseTree.rule;
        if(token != null){
            if(token.type == JsonLexer.STRING){
                String object = new String(token.text);
                return object;
            }else  if(token.type == JsonLexer.TRUE){
                return Boolean.TRUE;
            }else  if(token.type == JsonLexer.FALSE){
                return Boolean.FALSE;
            }else  if(token.type == JsonLexer.NULL){
                return JsonLexer.JSON_NULL;
            }else  if(token.type == JsonLexer.NUMBER){
                String object = new String(token.text);
                return object;
            }
        }
        if(rule.equals("array")){
            List array = new ArrayList();
            array.addAll( (Collection) visit(parseTree.children.get(1) ) );
            return array;
        }else if (rule.equals("element")){
            return visit(parseTree.children.get(0));
        }else if (rule.equals("json")){
            return visit(parseTree.children.get(0));
        }  else if (rule.equals("elements")){
            List array = new ArrayList();
            if(parseTree.children.size() >0 ){
                array.add(visit(parseTree.children.get(0)));
            }
            if(parseTree.children.size() >1 ){
                array.addAll((Collection) visit(parseTree.children.get(2)));
            }
            return array;
        }else  if(rule.equals("object")){
            Map map = new HashMap<>();
            for(ParseTreeJsonParser.ParseTree c :parseTree.children){
                map.put(visit(c), visit(c.children.get(0)));
            }
            return map;
        }else  if(rule.equals("id")){
            return visit(parseTree.children.get(0));
        }else  if(rule.equals("string")){
            return visit(parseTree.children.get(0));
        }else  if(rule.equals("number")){
            return visit(parseTree.children.get(0));
        }
        return null;
    }
}
