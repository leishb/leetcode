package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2020/1/8.
 */
public class _1106_Parsing_A_Boolean_Expression {


    /**
     * Accepted
     * @param expression
     * @return
     */
    public boolean parseBoolExpr(String expression) {
        char c = expression.charAt(0);
        if (c=='t') return true;
        if (c=='f') return false;
        if (c=='!'){
            return !parseBoolExpr(expression.substring(2, expression.length()-1));
        }
        if (c=='|'){
            List<String> tokens = parseTokens(expression.substring(2, expression.length()-1));
            for (String t : tokens){
                if (parseBoolExpr(t)) return true;
            }
            return false;
        }
        if (c=='&'){
            List<String> tokens = parseTokens(expression.substring(2, expression.length()-1));
            for (String t : tokens){
                if (!parseBoolExpr(t)) return false;
            }
            return true;
        }
        return false;
    }



    private List<String> parseTokens(String s){
        List<String> ans = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (char c : s.toCharArray()){
            if (c=='(') count++;
            if (c==')') count--;
            if (count==0 && c==','){
                ans.add(sb.toString());
                sb = new StringBuffer();
            }else {
                sb.append(c);
            }
        }
        if (sb.length()!=0) ans.add(sb.toString());
        return ans;
    }


    @Test
    public void test(){
        System.out.println(parseTokens("&(t,f,t),!(t)"));
    }
}
