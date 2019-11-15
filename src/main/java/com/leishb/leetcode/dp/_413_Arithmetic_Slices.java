package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/7.
 */
public class _413_Arithmetic_Slices {

    /**
     * Accepted
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        int len = 2;
        for (int i=2;i<A.length;i++){
            if (A[i]-A[i-1]==A[i-1]-A[i-2]){
                len +=1;
            }else {
                len = 2;
            }
            if (len>=3) res+=len-3+1;
        }
        return res;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numberOfArithmeticSlicesII(int[] A) {
        int[][] dp = new int[A.length][A.length];
        int res = 0;
        for (int i=2;i<A.length;i++){
            for (int j=i-1;j>=1;j--){
                long dif = (long) A[i]-(long) A[j];
                for (int k=j-1;k>=0;k--){
                    if ((long)A[j]-(long) A[k]==dif){
                        dp[i][j] += dp[j][k]+1;
                    }
                }
                res += dp[i][j];
            }
        }
        return res;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numberOfArithmeticSlicesII2(int[] A) {
        Map<Integer, Integer>[] dp = new Map[A.length];
        for (int i=0;i<A.length;i++){
            dp[i] = new HashMap<>();
        }
        int res = 0;
        for (int i=1;i<A.length;i++){
            for (int j=i-1;j>=0;j--){
                long dif = (long) A[i]-(long) A[j];
                if (dif>Integer.MAX_VALUE || dif<Integer.MIN_VALUE){
                    continue;
                }
                int d = (int)dif;
                dp[i].put(d, dp[i].getOrDefault(d, 0)+dp[j].getOrDefault(d, 0)+1);
                res += dp[j].getOrDefault(d, 0);
            }
        }
        return res;
    }

    @Test
    public void test(){
        Assert.assertTrue(numberOfArithmeticSlicesII(new int[]{0,2000000000,-294967296})==0);
    }
}
