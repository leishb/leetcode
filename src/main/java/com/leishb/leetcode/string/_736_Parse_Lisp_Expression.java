package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/12/19.
 */
public class _736_Parse_Lisp_Expression {


    /**
     * Accepted
     * @param expression
     * @return
     */
    public int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }


    private int eval(String expression, Map<String, Integer> parent){
        if (!expression.startsWith("(")){
            if (Character.isDigit(expression.charAt(0)) || expression.charAt(0)=='-'){
                return Integer.parseInt(expression);
            }
            return parent.get(expression);
        }
        Map<String, Integer> map = new HashMap<>();//create a new scope
        map.putAll(parent);
        List<String> tokens = parseTokens(expression.substring(expression.startsWith("(m")?6:5, expression.length()-1));
        if (expression.startsWith("(a")){//add
            return eval(tokens.get(0), map) + eval(tokens.get(1), map);
        }else if (expression.startsWith("(m")){//mult
            return eval(tokens.get(0), map) * eval(tokens.get(1), map);
        }else { // let
            for (int i=0;i+1<tokens.size();i+=2){
                map.put(tokens.get(i), eval(tokens.get(i+1), map));
            }
            return eval(tokens.get(tokens.size()-1), map);
        }
    }

    private List<String> parseTokens(String s){
        List<String> ans = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (char c : s.toCharArray()){
            if (c=='(')count++;
            if (c==')')count--;
            if (count==0 && c==' '){
                ans.add(sb.toString());
                sb = new StringBuffer();
            }else {
                sb.append(c);
            }
        }
        if (sb.length()>0) ans.add(sb.toString());
        return  ans;
    }
}
