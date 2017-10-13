package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Test;

/**
 * Created by me on 2017/9/27.
 */
@DynamicProgramming
public class PerfectSquares {

    @Test
    public void test(){
        for (int i=1;i<=100;i++){
            System.out.println(i + " -> " + numSquares(i));
        }
    }

    /**
     * Accepted
     * dp
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for (int i=0;i<dp.length;i++){
            dp[i]=Integer.MAX_VALUE;
        }
        dp[0]=0;
        for (int i=1;i<dp.length;i++){
            int numSqures = Integer.MAX_VALUE;
            for (int j=1;j*j<=i;j++){
                numSqures = Math.min(dp[i-j*j]+1,numSqures);
            }
            dp[i]=numSqures;
        }
        return dp[n];
    }

}
