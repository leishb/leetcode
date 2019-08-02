package com.leishb.leetcode.bfs;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/31.
 */
public class _688_Knight_Probability_in_Chessboard {


    int[][] dirs = new int[][]{{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{-2,1},{2,-1},{-2,-1}};

    /**
     * Accepted
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability(int N, int K, int r, int c) {
        double in = dfs(N, K, r, c, 0, new HashMap<>());
        return (in/Math.pow(8, K));
    }


    private double dfs(int N, int K, int r, int c, int steps, Map<String, Double> memo){
        if (r<0||c<0||r>=N||c>=N){
            return 0;
        }
        String key = r+"-"+c +"-"+steps;
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        double count = 0;
        if (steps==K){
            memo.put(key, 1.0);
            return 1;
        }
        for (int[] dir : dirs){
            count += dfs(N, K, r+dir[0], c+dir[1], steps+1, memo);
        }
        memo.put(key, count);
        return count;
    }


    /**
     * Accepted
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability2(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K+1];
        double count = 0;
        for (int i=0;i<=K;i++){
            for (int j=0;j<N;j++){
                for (int k=0;k<N;k++){
                    if (i==0 && j==r && k==c){
                        dp[j][k][i] = 1L;
                    }else if (i!=0){
                        for (int[] dir : dirs){
                            int x = j+dir[0];
                            int y = k+dir[1];
                            if (x>=0 && x<N && y>=0 && y<N){
                                dp[j][k][i] += dp[x][y][i-1];
                            }
                        }
                    }
                    if (i==K){
                        count+= dp[j][k][i];
                    }
                }
            }
        }
        return (count/Math.pow(8, K));
    }


    /**
     * Accepted
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability3(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K+1];
        for (int i=0;i<=K;i++){
            for (int j=0;j<N;j++){
                for (int k=0;k<N;k++){
                    if (i==0){
                        dp[j][k][i] = 1L;
                    }else{
                        for (int[] dir : dirs){
                            int x = j+dir[0];
                            int y = k+dir[1];
                            if (x>=0 && x<N && y>=0 && y<N){
                                dp[j][k][i] += dp[x][y][i-1];
                            }
                        }
                    }
                }
            }
        }
        return (dp[r][c][K]/Math.pow(8, K));
    }


    @Test
    public void test(){
        System.out.println(knightProbability3(3,2,0,0));

        long start = System.currentTimeMillis();
        System.out.println(knightProbability3(8,30,6,4));
        System.out.println(knightProbability(8,30,6,4));

        System.out.println("cost : " + (System.currentTimeMillis()-start));
    }
}
