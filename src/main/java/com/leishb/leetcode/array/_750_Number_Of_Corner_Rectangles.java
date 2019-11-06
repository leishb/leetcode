package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/29.
 */
public class _750_Number_Of_Corner_Rectangles {



    public int countCornerRectangles(int[][] grid) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==0) continue;
                for (int k=0;k<j;k++){
                    String key = k + "-"+j;
                    if (grid[i][k]==1){
                        map.put(key, map.getOrDefault(key, 0)+1);
                        res += map.get(key)-1;
                    }
                }
            }
        }
        return res;
    }

    public int countCornerRectangles2(int[][] grid) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i<grid.length;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==0) continue;
                for (int k : list){
                    String key = k + "-"+j;
                    if (grid[i][k]==1){
                        map.put(key, map.getOrDefault(key, 0)+1);
                        res += map.get(key)-1;
                    }
                }
                list.add(j);
            }
        }
        return res;
    }


    public int countCornerRectangles4(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][n];
        int res = 0;
        for (int i=0;i<grid.length;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==0) continue;
                for (int k : list){
                    if (grid[i][k]==1){
                        res += dp[k][j];
                        dp[k][j]++;
                    }
                }
                list.add(j);
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[][] grid = new int[200][200];
        for (int i=0;i<200;i++){
            Arrays.fill(grid[i], 1);
        }
        long start = System.currentTimeMillis();
        countCornerRectangles4(grid);
        System.out.println("cost : " + (System.currentTimeMillis()-start));
    }
}
