package com.leishb.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/18.
 */
public class _1130_Minimum_Cost_Tree_From_Leaf_Values {



    public int mctFromLeafValues(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        int[][] max = new int[arr.length][arr.length];
        for (int i=0;i<arr.length;i++){
            for (int j=i;j<arr.length;j++){
                if (i==j){
                    max[i][j] =arr[i];
                }else {
                    max[i][j] = Math.max(max[i][j-1], arr[j]);
                }
            }
        }
        for (int dif=2;dif<=arr.length;dif++){
            for(int i=0;i+dif-1<arr.length;i++){
                int j=i+dif-1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k=i;k+1<=j;k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j] + max[i][k] * max[k+1][j]);
                }
            }
        }
        return dp[0][arr.length-1];
    }


    @Test
    public void test(){
        Assert.assertTrue(mctFromLeafValues(new int[]{6,2,4})==32);
    }
}
