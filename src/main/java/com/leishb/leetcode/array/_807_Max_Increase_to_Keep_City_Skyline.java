package com.leishb.leetcode.array;

/**
 * Created by me on 2019/8/15.
 */
public class _807_Max_Increase_to_Keep_City_Skyline {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int ans = 0;
        int[] rows = new int[grid.length];
        int[] cols = new int[grid.length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid.length;j++){
                rows[i] = Math.max(rows[i], grid[i][j]);
                cols[j] = Math.max(cols[j], grid[i][j]);
            }
        }
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid.length;j++){
                ans+=  Math.min(rows[i], cols[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
