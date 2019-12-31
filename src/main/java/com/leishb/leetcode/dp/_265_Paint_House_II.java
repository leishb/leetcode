package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/11/1.
 */
public class _265_Paint_House_II {


    /**
     * Accepted
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if (costs.length==0) return 0;
        int[][] dp = new int[costs.length][costs[0].length];
        int n = costs.length, k = costs[0].length;
        for (int i=0;i<k;i++){
            dp[0][i] = costs[0][i];
        }
        int ans = Integer.MAX_VALUE;
        for (int i=1;i<n;i++){
            for (int j=0;j<k;j++){
                //min except j
                int min = Integer.MAX_VALUE;
                for (int x=0;x<k;x++){
                    if (x==j) continue;
                    min = Math.min(min, dp[i-1][x]);
                }
                dp[i][j] = min + costs[i][j];
            }
        }
        for (int i=0;i<k;i++){
            ans = Math.min(ans, dp[n-1][i]);
        }
        return ans;
    }


    /**
     * Accepted
     * @param costs
     * @return
     */
    public int minCostII2(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;
        int ans = Integer.MAX_VALUE;
        int minId1 = -1, minId2 = -1;
        for (int i=0;i<n;i++) {
            int prev1 = minId1, prev2 = minId2;
            minId1 = -1 ; minId2 = -1;
            for (int j = 0; j < k; j++) {
                if (i>0){
                    costs[i][j] = costs[i][j] + (j==prev1? costs[i-1][prev2] : costs[i-1][prev1]);
                }
                if (minId1==-1){
                    minId1 = j;
                }else if (costs[i][j] <= costs[i][minId1]){
                    minId2 = minId1;
                    minId1 = j;
                }else if (minId2 == -1 || costs[i][j] <= costs[i][minId2]){
                    minId2 = j;
                }
            }
        }
        for (int i=0;i<k;i++){
            ans = Math.min(ans, costs[n-1][i]);
        }
        return ans;
    }

    /**
     * Accepted
     * @param costs
     * @return
     */
    public int minCostII3(int[][] costs) {
        if(costs.length==0) return 0;
        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];
        int ans = Integer.MAX_VALUE;
        int minId1 = -1, minId2 = -1 ;
        for (int i=0;i<n ;i++ ) {
            for (int j=0;j<k ;j++ ) {
                if (i==0) {
                    dp[i][j] = costs[i][j];
                }else{
                    if (j==minId1) {
                        dp[i][j] = dp[i-1][minId2] + costs[i][j];
                    }else{
                        dp[i][j] = dp[i-1][minId1] + costs[i][j];
                    }
                }
            }
            minId1 = -1;
            minId2 = -1;
            for (int j=0;j<k ;j++ ) {
                if (minId1==-1) {
                    minId1 = j;
                }else if (dp[i][minId1] > dp[i][j]) {
                    minId2 = minId1;
                    minId1 = j;
                }else if (minId2 == -1) {
                    minId2 = j;
                }else if (dp[i][minId2] > dp[i][j]) {
                    minId2 = j;
                }
            }
        }
        for (int i=0;i<k ;i++ ) {
            ans = Math.min(ans, dp[n-1][i]);
        }
        return ans;
    }


    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[0][i] = arr[0][i];
        }
        for(int i=1;i<n;i++){
            int min1 = -1, min2 = -1;
            for(int j=0;j<n;j++){
                if(min1==-1){
                    min1 = j;
                }else if(dp[i-1][min1] > dp[i-1][j]){
                    min2 = min1;
                    min1 = j;
                }else if(min2 == -1){
                    min2 = j;
                }else if(dp[i-1][min2] > dp[i-1][j]){
                    min2 = j;
                }
            }
            for(int j=0;j<n;j++){
                if (j!=min1){
                    dp[i][j] = arr[i][j] + dp[i-1][min1];
                }else{
                    dp[i][j] = arr[i][j] + dp[i-1][min2];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            ans = Math.min(ans, dp[n-1][i]);
        }
        return ans;
    }



    @Test
    public void test(){
        minFallingPathSum(new int[][]{{-73,61,43,-48,-36}
                ,{3,30,27,57,10}
                ,{96,-76,84,59,-15}
                ,{5,-49,76,31,-7}
                ,{97,91,61,-46,67}} );
    }
}
