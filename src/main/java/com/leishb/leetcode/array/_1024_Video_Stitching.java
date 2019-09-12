package com.leishb.leetcode.array;

import java.util.Arrays;

/**
 * Created by me on 2019/9/10.
 */
public class _1024_Video_Stitching {


    /**
     * Accepted
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        int start = 0;
        int ans = 0;
        while (true){
            int maxEnd = 0;
            for (int[] clip : clips){
                if (clip[0]<=start){
                    maxEnd = Math.max(maxEnd, clip[1]);
                }
            }
            ans+=1;
            if (maxEnd>=T){
                break;
            }
            if (maxEnd<=start){
                return -1;
            }
            start = maxEnd;
        }
        return ans;
    }


    public int videoStitching2(int[][] clips, int T) {
        int[] dp = new int[T+1];
        Arrays.fill(dp, T+1);
        dp[0]=0;
        for (int i=1;i<=T;i++){
            for (int[] clip : clips){
                if (clip[0]<=i && clip[1]>=i){
                    dp[i] = Math.min(dp[i], dp[clip[0]]+1);
                }
            }
            if (dp[i] == T+1) return -1;
        }
        return dp[T];
    }
}
