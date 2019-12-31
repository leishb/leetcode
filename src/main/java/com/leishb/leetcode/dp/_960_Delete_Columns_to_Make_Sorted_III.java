package com.leishb.leetcode.dp;

import java.util.Arrays;

/**
 * Created by me on 2019/12/5.
 */
public class _960_Delete_Columns_to_Make_Sorted_III {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        int m = A.length, n = A[0].length() , res = n-1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i=0;i<n;i++){
            for (int j=0;j<i;j++){
                int k = 0;
                for (;k<m;k++){
                    if (A[k].charAt(j) > A[k].charAt(i)){
                        break;
                    }
                }
                if (k==m && dp[j]+1 > dp[i]){
                    dp[i] = dp[j]+1;
                }
            }
            res = Math.min(res, n-dp[i]);
        }
        return res;
    }




}
