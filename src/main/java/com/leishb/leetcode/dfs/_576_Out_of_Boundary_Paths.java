package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/31.
 */
public class _576_Out_of_Boundary_Paths {

    int M=1000000007;

    /**
     * Accepted
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        return (int)dfs(m, n, N, i, j, new HashMap<>());
    }


    private long dfs(int m, int n, int N, int i, int j, Map<String, Long> memo){
        if (i<0 || j<0 || i>=m || j>=n){
            return 1;
        }
        String key = i+"-" + j+"-"+N;
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        if (N==0){
            return 0;
        }
        long count = (dfs(m, n, N-1, i-1, j, memo)+dfs(m, n, N-1, i+1, j, memo)) + (dfs(m, n, N-1, i, j-1, memo)+dfs(m, n, N-1, i, j+1, memo));
        count = count%M;
        memo.put(key, count);
        return count;
    }


    public int findPaths2(int m, int n, int N, int i, int j) {
        int[][][] dp = new int[m][n][N+1];
        int count = 0;
        for (int k=1;k<=N;k++){
            for (int x=0;x<m;x++){
                for (int y=0;y<n;y++){
                    int hor = 0;
                    int ver = 0;
                    if (x-1>=0){
                        hor += dp[x-1][y][k-1];
                    }else if (x-1<0){
                        hor += k==1?1:0;
                    }
                    if (x+1<m){
                        hor += dp[x+1][y][k-1];
                    }else if (x+1>=m){
                        hor += k==1?1:0;
                    }
                    if (y-1>=0){
                        ver += dp[x][y-1][k-1];
                    }else if (y-1<0){
                        ver += k==1?1:0;
                    }
                    if (y+1<n){
                        ver += dp[x][y+1][k-1];
                    }else if (y+1>=n){
                        ver += k==1?1:0;
                    }
                    dp[x][y][k] = (hor%M+ver%M)%M;
                    if (x==i && y==j){
                        count +=dp[x][y][k];
                        count%=M;
                    }
                }
            }
        }
        return count;
    }


    public int findPaths3(int m, int n, int N, int i, int j) {
        int[][][] dp = new int[m][n][N+1];
        for (int k=1;k<=N;k++){
            for (int x=0;x<m;x++){
                for (int y=0;y<n;y++){
                    int hor = 0;
                    int ver = 0;
                    if (x-1>=0){
                        hor += dp[x-1][y][k-1];
                    }else if (x-1<0){
                        hor +=1;
                    }
                    if (x+1<m){
                        hor += dp[x+1][y][k-1];
                    }else if (x+1>=m){
                        hor +=1;
                    }
                    if (y-1>=0){
                        ver += dp[x][y-1][k-1];
                    }else if (y-1<0){
                        ver +=1;
                    }
                    if (y+1<n){
                        ver += dp[x][y+1][k-1];
                    }else if (y+1>=n){
                        ver +=1;
                    }
                    dp[x][y][k] = (hor%M+ver%M)%M;
                }
            }
        }
        return dp[i][j][N]%M;
    }

    @Test
    public void test(){
        Assert.assertTrue(findPaths(2,2,2,0,0)==6);
        Assert.assertTrue(findPaths(1,3,3,0,1)==12);
        Assert.assertTrue(findPaths(8,50,23,5,26)==914783380);

    }
}
