package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/11/13.
 */
public class _568_Maximum_Vacation_Days {


    /**
     * Accepted
     * @param flights
     * @param days
     * @return
     */
    public int maxVacationDays(int[][] flights, int[][] days) {
        //n : cities, k : weeks
        int n = days.length, k = days[0].length;
        int[][] memo = new int[n][k];
        for (int i=0;i<memo.length;i++){
            Arrays.fill(memo[i], -1);
        }
        return dfs(flights, days, 0, 0, memo);
    }


    /**
     *
     * @param flights
     * @param days
     * @param city leave city
     * @param weeks
     * @param memo
     * @return vacdays when leave city
     */
    private int dfs(int[][] flights, int[][] days, int city, int weeks, int[][] memo) {
        if (weeks==days[0].length){
            return 0;
        }
        if (memo[city][weeks]!=-1){
            return memo[city][weeks];
        }
        int max = 0;
        // Fly to other city to stay for this week
        for (int i=0;i<flights[city].length;i++){
            if (i==city || flights[city][i] == 1){
                max = Math.max(max, dfs(flights, days, i, weeks+1, memo) + days[i][weeks]);
            }
        }
        memo[city][weeks] = max;
        return max;
    }



    @Test
    public void test(){
        Assert.assertTrue(maxVacationDays(new int[][]{{0,1,0},{0,0,0},{0,0,0}}, new int[][]{{0,0,7},{2,7,7},{7,7,7}})==16);
        Assert.assertEquals(14, maxVacationDays(new int[][]{{0,1,0},{0,0,1},{0,0,0}}, new int[][]{{0,0,0},{0,0,0},{7,7,7}}));
        Assert.assertTrue(maxVacationDays(new int[][]{{0,1,1},{0,0,0},{1,1,0}}, new int[][]{{2,2,7},{0,4,2},{7,3,0}})==17);
    }
}
