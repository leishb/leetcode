package com.leishb.leetcode.bfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/7/15.
 */
public class _542_01_Matrix {


    /**
     * Accepted
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (matrix[i][j]==0){
                    queue.offer(new int[]{i, j});
                }else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        while (!queue.isEmpty()){
            int[] cell = queue.poll();
            for (int[] d : dir){
                int r = cell[0]+d[0];
                int c = cell[1]+d[1];
                if (r<0 || r>=matrix.length||c<0 || c>=matrix[0].length || matrix[r][c]<=matrix[cell[0]][cell[1]]+1){
                    continue;
                }
                queue.offer(new int[]{r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]]+1;
            }
        }
        return matrix;
    }


    @Test
    public void test(){
        updateMatrix(new int[][]{{0,0,0}, {0, 1, 0}, {1, 1, 1}});

        updateMatrix(new int[][]{{1,0,1,1,0,0,1,0,0,1},
                {0,1,1,0,1,0,1,0,1,1},
                {0,0,1,0,1,0,0,1,0,0},
                {1,0,1,0,1,1,1,1,1,1},
                {0,1,0,1,1,0,0,0,0,1},
                {0,0,1,0,1,1,1,0,1,0},
                {0,1,0,1,0,1,0,0,1,1},
                {1,0,0,0,1,1,1,1,0,1},
                {1,1,1,1,1,1,1,0,1,0},
                {1,1,1,1,0,1,0,0,1,1}});
    }
}
