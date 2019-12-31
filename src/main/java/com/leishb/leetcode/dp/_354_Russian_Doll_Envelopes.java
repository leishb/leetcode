package com.leishb.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/12/10.
 */
public class _354_Russian_Doll_Envelopes {


    /**
     * Accepted
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length==0) return 0;
        Arrays.sort(envelopes, (e1, e2)-> e1[0]==e2[0]?e1[1]-e2[1]:e1[0]-e2[0]);
        int[] dp = new int[envelopes.length];
        int ans = 1;
        dp[0] = 1;
        for (int i=1;i<envelopes.length;i++){
            dp[i] = 1;
            for (int j=i-1;j>=0;j--){
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    /**
     * Accepted
     * @param envelopes
     * @return
     */
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int[] ev : envelopes){
            int i = Arrays.binarySearch(dp, 0, len, ev[1]);
            if (i<0) i = -(i+1);
            dp[i] = ev[1];
            if (i==len)len++;
        }
        return len;
    }



    @Test
    public void test(){
        int[] nums = new int[]{1,1,1,1};
        System.out.println(Arrays.binarySearch(nums, 1));
    }
}
