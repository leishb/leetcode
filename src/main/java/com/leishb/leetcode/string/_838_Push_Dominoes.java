package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/19.
 */
public class _838_Push_Dominoes {

    public String pushDominoes(String dominoes) {
        dominoes = 'L' + dominoes + 'R';
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 1; j < dominoes.length(); ++j) {
            if (dominoes.charAt(j) == '.') continue;
            int middle = j - i - 1;
            if (i > 0) res.append(dominoes.charAt(i));
            if (dominoes.charAt(i) == dominoes.charAt(j))
                for (int k = 0; k < middle; k++) res.append(dominoes.charAt(i));
            else if (dominoes.charAt(i) == 'L' && dominoes.charAt(j) == 'R')
                for (int k = 0; k < middle; k++) res.append('.');
            else {
                for (int k = 0; k < middle / 2; k++) res.append('R');
                if (middle % 2 == 1) res.append('.');
                for (int k = 0; k < middle / 2; k++) res.append('L');
            }
            i = j;
        }
        return res.toString();
    }


    @Test
    public void test(){
        Assert.assertTrue(pushDominoes(".L.R...LR..L..").equals("LL.RR.LLRRLL.."));
        Assert.assertTrue(pushDominoes("RR.L").equals("RR.L"));
        Assert.assertTrue(pushDominoes("L").equals("L"));
        Assert.assertTrue(pushDominoes("L.").equals("L."));
        Assert.assertTrue(pushDominoes(".L").equals("LL"));
        Assert.assertTrue(pushDominoes("R.").equals("RR"));
    }
}
