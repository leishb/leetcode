package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2017/12/5.
 */
public class BaseCaculator {

    @Test
    public void test(){
        Assert.assertTrue(calculate("1+2+3")==6);
        Assert.assertTrue(calculate("1+12+3")==16);
        Assert.assertTrue(calculate("1+12+3+10")==26);
        Assert.assertTrue(calculate("1+2-3")==0);
        Assert.assertTrue(calculate("1+2-3*2+3")==0);
        Assert.assertTrue(calculate("1+2-3*2")==-3);
        Assert.assertTrue(calculate("1 + 2 - 3 * 2")==-3);
        Assert.assertTrue(calculate("1+2-3/2")==2);
        Assert.assertTrue(calculate("1 + 2 - 3/ 2")==2);
        Assert.assertTrue(calculate("3/ 2")==1);
        Assert.assertTrue(calculate("3")==3);
        Assert.assertTrue(calculate("2*3")==6);
        Assert.assertTrue(calculate("0*0")==0);


        System.out.println(Integer.parseInt("00"));
        System.out.println(Integer.parseInt("01"));
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        int cur = 0;
        int ans = 0;
        Stack<Integer> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c>='0' && c<='9'){
                cur = cur*10 + (c-'0');
            }else if (c == '+'){
                s1.add(cur);
                helper(s1, s2);
                s2.add(c);
                cur = 0;
            }else if (c == '-'){
                s1.add(cur);
                helper(s1, s2);
                s2.add(c);
                cur = 0;
            }else if (c == '*'){
                s1.add(cur);
                helper(s1, s2);
                s2.add(c);
                cur = 0;
            }else if (c == '/'){
                s1.add(cur);
                helper(s1, s2);
                s2.add(c);
                cur = 0;
            }
        }
        s1.add(cur);
        helper(s1, s2);
        while (!s1.isEmpty()){
            int i = s1.pop();
            if (s2.isEmpty()){
                ans += i;
            }else if (s2.pop()=='+'){
                ans += i;
            }else {
                ans -= i;
            }
        }
        return ans;
    }

    private void helper(Stack<Integer> s1, Stack<Character> s2){
        if (!s2.isEmpty() && (s2.peek()=='*' || s2.peek()=='/')){
            char cc = s2.pop();
            if (cc == '*'){
                s1.add(s1.pop()*s1.pop());
            }else {
                int p = s1.pop();
                int q = s1.pop();
                s1.add(q/p);
            }
        }
    }
}
