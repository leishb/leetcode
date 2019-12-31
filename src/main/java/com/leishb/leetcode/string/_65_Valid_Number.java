package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/12/13.
 */
public class _65_Valid_Number {



    public boolean isNumber(String s) {
        s = s.trim();
        if (s.startsWith("+") || s.startsWith("-")){
            s = s.substring(1);
        }
        return isInteger(s) || isFloat(s) || isScienceNum(s);
    }

    private boolean isInteger(String num){
        for (char c : num.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return num.length()!=0;
    }

    private boolean isFloat(String num){
        if (num.indexOf(".")==-1 || num.length()==1) return false;
        int pos = num.indexOf(".");
        String first = num.substring(0, pos);
        String second = num.substring(pos+1);
        if ("".equals(first)) return isInteger(second);
        if ("".equals(second)) return isInteger(first);
        return isInteger(first) && isInteger(second);
    }

    private boolean isScienceNum(String num){
        if (num.indexOf("e")==-1 || num.length()==1) return false;
        int pos = num.indexOf("e");
        String first = num.substring(0, pos);
        String second = num.substring(pos+1);
        if (first.startsWith("+") || first.startsWith("-")){
            first = first.substring(1);
        }
        if (second.startsWith("+") || second.startsWith("-")){
            second = second.substring(1);
        }
        return isInteger(second) && (isInteger(first) || isFloat(first));
    }


    @Test
    public void testValidNum(){
        Assert.assertTrue(!isNumber("e-e"));
        Assert.assertTrue(isNumber("123"));
        Assert.assertTrue(isNumber("+123"));
        Assert.assertTrue(isNumber("+12.3"));
        Assert.assertTrue(isNumber("+12e3"));
        Assert.assertTrue(isNumber("+12e-3"));
        Assert.assertTrue(!isNumber("+a12e-3"));
        Assert.assertTrue(!isNumber(".1.23"));
        Assert.assertTrue(isNumber(".123"));
        Assert.assertTrue(isNumber(".1e2"));
        Assert.assertTrue(isNumber("3."));
        Assert.assertTrue(!isNumber("."));
        Assert.assertTrue(!isNumber("0.."));
    }
}
