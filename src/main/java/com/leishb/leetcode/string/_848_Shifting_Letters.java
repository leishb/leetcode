package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/28.
 */
public class _848_Shifting_Letters {


    /**
     * Accepted
     * @param S
     * @param shifts
     * @return
     */
    public String shiftingLetters(String S, int[] shifts) {
        int[] sums = new int[shifts.length];
        sums[sums.length-1] = shifts[shifts.length-1]%26;
        for (int i=shifts.length-2;i>=0;i--){
            sums[i] = (sums[i+1]+shifts[i]%26)%26;
        }
        char[] cs = new char[S.length()];
        for (int i=0;i<S.length();i++){
            char c = S.charAt(i);
            if (sums[i] <= 'z'-c){
                cs[i] = (char) (c+sums[i]);
            }else {
                cs[i] = (char) ('a' + sums[i]-1-('z'-c));
            }
        }
        return new String(cs);
    }

    @Test
    public void test(){
        Assert.assertEquals("rpl", shiftingLetters("abc", new int[]{3,5,9}));
        Assert.assertEquals("omi", shiftingLetters("xyz", new int[]{3,5,9}));
    }
}
