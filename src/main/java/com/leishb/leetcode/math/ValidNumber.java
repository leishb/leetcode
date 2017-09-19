package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/18.
 */
public class ValidNumber {

    @Test
    public void testValidNum(){
        Assert.assertTrue(validNum("123"));
        Assert.assertTrue(validNum("+123"));
        Assert.assertTrue(validNum("+12.3"));
        Assert.assertTrue(validNum("+12e3"));
        Assert.assertTrue(validNum("+12e-3"));
        Assert.assertTrue(!validNum("+a12e-3"));
        Assert.assertTrue(!validNum(".1.23"));
        Assert.assertTrue(validNum(".123"));
        Assert.assertTrue(validNum(".1e2"));
    }

    /**
     * accepted
     * @param s
     * @return
     */
    public boolean validNum(String s){
        String str = s.trim();
        if (str.length()==0){
            return false;
        }
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = false;
        for (int i = 0;i< str.length();i++){
            char c = str.charAt(i);
            if (c=='.'){
                if (pointSeen || eSeen){
                    return false;
                }
                pointSeen = true;
            }else if (c=='e'){
                if (eSeen || !numberSeen){
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            }else if (c >='0' && c<='9'){
                numberSeen = true;
                numberAfterE = true;
            }else if ((c=='+'||c=='-')){
                if (i!=0&&str.charAt(i-1) != 'e'){
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return numberSeen && numberAfterE;
    }
}
