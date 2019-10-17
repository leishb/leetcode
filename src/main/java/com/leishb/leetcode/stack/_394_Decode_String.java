package com.leishb.leetcode.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/10/17.
 */
public class _394_Decode_String {

    public String decodeString(String s) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        int n = 1;
        while (i<s.length()){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                int k = 0;
                while (i<s.length() && Character.isDigit(s.charAt(i))){
                    k = k*10 + s.charAt(i)-'0';
                    i++;
                }
                n = k;
            }else if (c=='['){
                int j = i;
                int count = 0;
                while (j<s.length()){
                    if (s.charAt(j)=='[')count++;
                    if (s.charAt(j)==']')count--;
                    if (count==0)break;
                    j++;
                }
                String inner = decodeString(s.substring(i+1, j));
                for (int k=0;k<n;k++){
                    sb.append(inner);
                }
                i = j+1;
            }else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }


    @Test
    public void test(){
        org.junit.Assert.assertTrue(decodeString("2[a3[ac]d]").equals("aacacacdaacacacd"));
    }
}
