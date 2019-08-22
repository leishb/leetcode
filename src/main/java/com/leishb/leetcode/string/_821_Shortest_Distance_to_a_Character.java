package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/8/19.
 */
public class _821_Shortest_Distance_to_a_Character {


    /**
     * Accepted
     * @param S
     * @param C
     * @return
     */
    public int[] shortestToChar(String S, char C) {
        int[] ans = new int[S.length()];
        int[] left = new int[S.length()];
        Arrays.fill(left, Integer.MAX_VALUE);
        int[] right = new int[S.length()];
        Arrays.fill(right, Integer.MAX_VALUE);
        int prev = -1;
        for (int i=0;i<left.length;i++){
            if (S.charAt(i)==C){
                left[i] = 0;
                prev = i;
            }else if (prev!=-1){
                left[i] = i-prev;
            }
        }
        prev = -1;
        for (int i=right.length-1;i>=0;i--){
            if (S.charAt(i)==C){
                right[i] = 0;
                prev = i;
            }else if (prev!=-1){
                right[i] = prev-i;
            }
        }
        for (int i=0;i<S.length();i++){
            ans[i] = Math.min(left[i], right[i]);
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertArrayEquals(shortestToChar("loveleetcode", 'e'), new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0});
    }
}
