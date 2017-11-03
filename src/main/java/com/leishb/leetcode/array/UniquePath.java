package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/2.
 */
@DynamicProgramming
public class UniquePath {


    @Test
    public void test(){
        int[][] obstacleGrid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        Assert.assertTrue(uniquePathsWithObstacles(obstacleGrid)==2);
        Assert.assertTrue(uniquePathsWithObstacles(new int[][]{{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}})==0);
        Assert.assertTrue(uniquePathsWithObstacles(new int[][]{{0,0,0,0,0},{1,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}})==1);


        Assert.assertTrue(uniquePathsWithObstacles2(obstacleGrid)==2);
        Assert.assertTrue(uniquePathsWithObstacles2(new int[][]{{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}})==0);
        Assert.assertTrue(uniquePathsWithObstacles2(new int[][]{{0,0,0,0,0},{1,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}})==1);



        Assert.assertTrue(uniquePathsWithObstaclesInPlace(new int[][]{{0,0,0},{0,1,0},{0,0,0}})==2);
        Assert.assertTrue(uniquePathsWithObstaclesInPlace(new int[][]{{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}})==0);
        Assert.assertTrue(uniquePathsWithObstaclesInPlace(new int[][]{{0,0,0,0,0},{1,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}})==1);

    }

    /**
     * Accepted
     * @param m
     * @param n
     * @return
     */
    public int uniquePath(int m, int n){
        if (m==0 || n==0){
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i=0;i<n;i++){
            dp[0][i]=1;
        }
        for (int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    /**
     * Accepted
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length==0 || obstacleGrid[0].length==0){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0]=obstacleGrid[0][0]==0 ? 1 : 0;
        for (int i=1;i<m;i++){
            if (obstacleGrid[i][0]==0){
                dp[i][0]=dp[i-1][0];
            }else {
                dp[i][0]=0;
            }
        }
        for (int i=1;i<n;i++){
            if (obstacleGrid[0][i]==0){
                dp[0][i]=dp[0][i-1];
            }else {
                dp[0][i]=0;
            }
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (obstacleGrid[i][j]==0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }else {
                    dp[i][j]=0;
                }
            }
        }
        return dp[m-1][n-1];
    }


    /**
     * Accepted
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0]=obstacleGrid[i-1][0]==1 ? 0 : dp[i-1][0];
        }
        for (int i=1;i<n;i++){
            dp[0][i]=obstacleGrid[0][i-1]==1 ? 0 : dp[0][i-1];
        }

        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (obstacleGrid[i-1][j]==1 && obstacleGrid[i][j-1] != 1){
                    dp[i][j]=dp[i][j-1];
                }else if (obstacleGrid[i-1][j] !=1 && obstacleGrid[i][j-1] == 1){
                    dp[i][j]=dp[i-1][j];
                }else if (obstacleGrid[i-1][j] ==1 && obstacleGrid[i][j-1] == 1){
                    dp[i][j]=0;
                }else {
                    dp[i][j]=dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return obstacleGrid[m-1][n-1]==1 ? 0 :  dp[m-1][n-1];
    }


    public int uniquePathsWithObstaclesInPlace(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        obstacleGrid[0][0] = obstacleGrid[0][0]==1 ? 0 : 1;
        for (int i=1;i<m;i++){
            if (obstacleGrid[i][0]==1){
                obstacleGrid[i][0]=0;
            }else {
                obstacleGrid[i][0]=obstacleGrid[i-1][0];
            }
        }
        for (int i=1;i<n;i++){
            if (obstacleGrid[0][i]==1){
                obstacleGrid[0][i]=0;
            }else {
                obstacleGrid[0][i]=obstacleGrid[0][i-1];
            }
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (obstacleGrid[i][j]==0){
                    obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }else {
                    obstacleGrid[i][j]=0;
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}
