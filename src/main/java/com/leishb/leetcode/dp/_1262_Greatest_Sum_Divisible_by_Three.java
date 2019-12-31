package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/11/28.
 */
public class _1262_Greatest_Sum_Divisible_by_Three {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int[][] dp = new int[nums.length+1][3];
        for (int i=1;i<=nums.length;i++){
            for (int j=0;j<3;j++){
                dp[i][j] = dp[i-1][j];
            }
            int mod = nums[i-1]%3;
            if (mod==0){
                dp[i][0] += nums[i-1];
                if (dp[i-1][1]!=0) dp[i][1] =dp[i-1][1] + nums[i-1];
                if (dp[i-1][2]!=0) dp[i][2] = dp[i-1][2] + nums[i-1];
            }else if (mod==1){
                if (dp[i-1][2]!=0) dp[i][0]= Math.max(dp[i][0], dp[i-1][2] + nums[i-1]);
                if (dp[i-1][1]!=0) dp[i][2]= Math.max(dp[i][2], dp[i-1][1] + nums[i-1]);
                dp[i][1] = Math.max(dp[i][1], dp[i-1][0] + nums[i-1]);
            }else {
                if (dp[i-1][2]!=0) dp[i][1]= Math.max(dp[i][1], dp[i-1][2] + nums[i-1]);
                if (dp[i-1][1]!=0) dp[i][0]= Math.max(dp[i][0], dp[i-1][1] + nums[i-1]);
                dp[i][2] = Math.max(dp[i][2], dp[i-1][0] + nums[i-1]);
            }
        }
        return dp[nums.length][0];
    }



    public int maxSumDivThree2(int[] nums) {
        int[][] dp = new int[nums.length + 1][3];
        dp[0] = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i=1;i<=nums.length;i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            int mod = nums[i - 1] % 3;
            if (mod==0){
                dp[i][0] += nums[i-1];
                dp[i][1] = dp[i-1][1] + nums[i-1];
                dp[i][2] = dp[i-1][2] + nums[i-1];
            }else if (mod==1){
                dp[i][0]= Math.max(dp[i][0], dp[i-1][2] + nums[i-1]);
                dp[i][2]= Math.max(dp[i][2], dp[i-1][1] + nums[i-1]);
                dp[i][1] = Math.max(dp[i][1], dp[i-1][0] + nums[i-1]);
            }else {
                dp[i][1]= Math.max(dp[i][1], dp[i-1][2] + nums[i-1]);
                dp[i][0]= Math.max(dp[i][0], dp[i-1][1] + nums[i-1]);
                dp[i][2] = Math.max(dp[i][2], dp[i-1][0] + nums[i-1]);
            }
        }
        return dp[nums.length][0];
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int maxSumDivThree3(int[] nums) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i=1;i<=nums.length;i++) {
            int[] dp2 = new int[3];
            for (int j = 0; j < 3; j++) {
                dp2[j] = dp[j];
            }
            int mod = nums[i - 1] % 3;
            for (int j = 0; j<3 ;j++){
                dp2[(j+mod)%3] = Math.max(dp2[(j+mod)%3], dp[j] + nums[i-1]);
            }
            dp = dp2;
        }
        return dp[0];
    }


    public int maxSumDivThree4(int[] nums) {
        int[][] dp = new int[nums.length + 1][3];
        dp[0] = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int i=1;i<=nums.length;i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            int mod = nums[i - 1] % 3;
            for (int j = 0; j<3 ;j++){
                dp[i][(j+mod)%3] = Math.max(dp[i][(j+mod)%3], dp[i-1][j] + nums[i-1]);
            }
        }
        return dp[nums.length][0];
    }



    @Test
    public void test(){
        maxSumDivThree(new int[]{3,6,5,1,8});
    }
}
