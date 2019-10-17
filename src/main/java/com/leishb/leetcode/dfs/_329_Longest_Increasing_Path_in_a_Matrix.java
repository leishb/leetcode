package com.leishb.leetcode.dfs;

import org.junit.Test;

/**
 * Created by me on 2019/7/1.
 */
public class _329_Longest_Increasing_Path_in_a_Matrix {

    /*

    [
        [9,9,4],
        [6,6,8],
        [2,1,1]
    ]
    output 4; 1,2,6,9
    */

    /**
     * Accepted
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length==0 || matrix[0].length==0){
            return 0;
        }
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                boolean[][] visited = new boolean[m][n];
                max = Math.max(max, dfs(matrix, i, j, visited, memo));
            }
        }
        return max;
    }



    private int dfs(int[][] matrix, int i, int j, boolean[][] visisted, int[][] memo){
        if (i<0 || j<0 || i>=matrix.length || j>=matrix[0].length){
            return 0;
        }
        //attention : visited and memo judge orders
        //visited array is not necessary
        if (memo[i][j] > 0){
            return memo[i][j];
        }
        if (visisted[i][j]){
            return 0;
        }
        visisted[i][j] = true;
        int ans = 0;
        if (i>0 && matrix[i-1][j] > matrix[i][j]){
            ans = Math.max(ans, dfs(matrix, i-1, j, visisted, memo));
        }
        if (j>0 && matrix[i][j-1] > matrix[i][j]){
            ans = Math.max(ans, dfs(matrix, i, j-1, visisted, memo));
        }
        if (i<matrix.length-1 && matrix[i+1][j] > matrix[i][j]){
            ans = Math.max(ans, dfs(matrix, i+1, j, visisted, memo));
        }
        if (j<matrix[0].length-1 && matrix[i][j+1] > matrix[i][j]){
            ans = Math.max(ans, dfs(matrix, i, j+1, visisted, memo));
        }
        ans += 1;
        memo[i][j] = ans;
        return ans;
    }

    public int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        if(matrix.length==0) return 0;
        int[][] count = new int[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length ;i++ ) {
            for (int j=0;j<matrix[0].length ;j++ ) {
                if (count[i][j] == 0) {
                    ans = Math.max(ans, dfs(matrix, i, j, count));
                }
            }
        }
        return ans;
    }


    int[][]  dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1,0}};

    private int dfs(int[][] matrix, int i, int j, int[][] count){
        int m = matrix.length;
        int n = matrix[0].length;
        if (count[i][j] > 0) {
            return count[i][j];
        }
        int max = 1;
        for (int[] dir : dirs) {
            int x = i+dir[0];
            int y = j+dir[1];
            if (x < 0 || y< 0 || x>=m || y>= n || matrix[i][j] >= matrix[x][y]) {
                continue;
            }
            max = Math.max(max, dfs(matrix, x, y, count)+1);
        }
        count[i][j] = max;
        return max;
    }

    @Test
    public void test(){
        System.out.println(longestIncreasingPath(new int[][]{{7,6,1,1},
                {2,7,6,0},
                {1,3,5,1},
                {6,6,3,2}}));
    }
}
