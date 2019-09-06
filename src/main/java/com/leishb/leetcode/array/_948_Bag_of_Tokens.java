package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/9/4.
 */
public class _948_Bag_of_Tokens {

    /**
     * Accepted
     * @param tokens
     * @param P
     * @return
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        if (tokens.length==0) return 0;
        Arrays.sort(tokens);
        int i=0;
        int j=tokens.length-1;
        int points = 0;
        while (i<j){
            if (P >= tokens[i]){
                points+=1;
                P-=tokens[i];
                i++;
            }else if (points>0){
                points-=1;
                P+=tokens[j];
                j--;
            }else {
                break;
            }
        }
        points += (P>=tokens[i])?1:0;
        return points;
    }

    @Test
    public void test(){
        Assert.assertTrue(bagOfTokensScore(new int[]{100}, 50)==0);
        Assert.assertTrue(bagOfTokensScore(new int[]{100, 200}, 150)==1);
        Assert.assertTrue(bagOfTokensScore(new int[]{100, 200, 300, 400}, 200)==2);
    }
}
