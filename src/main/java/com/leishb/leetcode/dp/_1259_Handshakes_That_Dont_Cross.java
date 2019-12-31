package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/12/5.
 */
public class _1259_Handshakes_That_Dont_Cross {


    /**
     * Accepted
     * @param num_people
     * @return
     */
    public int numberOfWays(int num_people) {
        int M = 1_000_000_007;
        long[] dp = new long[num_people+1];
        if (num_people==2)return 1;
        dp[2] = 1;
        for (int i=4;i<=num_people;i+=2){
            for (int j=2;j<=i;j+=2){
                if (j==2 || j==i){
                    dp[i] = (dp[i]%M +  dp[i-2]%M)%M;
                }else {
                    dp[i] = (dp[i]%M + (dp[j-2] * dp[i-j])%M)%M;
                }
            }
        }
        return (int) dp[num_people];
    }



    @Test
    public void test(){
        numberOfWays(6);
    }
}
