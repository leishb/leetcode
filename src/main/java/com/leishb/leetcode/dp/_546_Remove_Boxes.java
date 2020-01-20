package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2020/1/7.
 */
public class _546_Remove_Boxes {


    /**
     * Accepted
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[101][101][101];
        return dfs(boxes, dp, 0, boxes.length-1, 0);
    }


    private int dfs(int[] boxes, int[][][] dp,  int l, int r, int k){
        if (l>r)return 0;
        if (dp[l][r][k] > 0) return dp[l][r][k];
        while (l < r && boxes[r-1] == boxes[r]){
            r--;
            k++;
        }
        dp[l][r][k] = dfs(boxes, dp, l, r-1, 0) + (k+1)*(k+1);
        for (int p=l;p<r;p++){
            if (boxes[p]==boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], dfs(boxes, dp, l, p, k+1) + dfs(boxes , dp, p+1, r-1, 0));
            }
        }
        return dp[l][r][k];
    }



    @Test
    public void test(){
        Assert.assertEquals(23, removeBoxes(new int[]{1,3,2,2,2,3,4,3,1}));
    }

}
