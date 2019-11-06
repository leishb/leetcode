package com.leishb.leetcode.dp;

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
}
