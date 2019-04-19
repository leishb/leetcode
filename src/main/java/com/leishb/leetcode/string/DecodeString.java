package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/4/17.
 */
public class DecodeString {


    /**
     * Accepted
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        String res = "";
        while (idx<s.length()){
            if (Character.isDigit(s.charAt(idx))){
                int count = 0;
                while (Character.isDigit(s.charAt(idx))){
                    count = count*10 + (s.charAt(idx)-'0');
                    idx++;
                }
                countStack.push(count);
            }else if (s.charAt(idx)=='['){
                resStack.push(res);
                res = "";
                idx++;
            }else if (s.charAt(idx)==']'){
                StringBuffer sb = new StringBuffer(resStack.pop());
                int repeat = countStack.pop();
                for (int i=0;i<repeat;i++){
                    sb.append(res);
                }
                res = sb.toString();
                idx++;
            }else {
                res += s.charAt(idx);
                idx++;
            }
        }
        return res;
    }


    @Test
    public void test(){
        org.junit.Assert.assertTrue(decodeString("2[a3[ac]d]").equals("aacacacdaacacacd"));
    }
}
