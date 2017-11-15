package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/14.
 */
public class GameOfLife {

    @Test
    public void test(){
        int[][] board = new int[][]{{0,0,0,0,0,0},{0,0,1,1,0,0},{0,1,0,0,1,0},{0,0,1,1,0,0},{0,0,0,0,0,0}};
        gameOflife(board);
        Assert.assertArrayEquals(new int[][]{{0,0,0,0,0,0},{0,0,1,1,0,0},{0,1,0,0,1,0},{0,0,1,1,0,0},{0,0,0,0,0,0}}, board);
    }


    public void gameOflife(int[][] board){
        if (board.length==0){
            return;
        }
        int m = board.length;
        int n = board[0].length;
        int[][] copy = new int[m][n];
        for (int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j]=board[i][j];
            }
        }
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                int live =countOfLiveNeighours(copy, i, j);
                if (copy[i][j]==1){
                    if (live<2 || live >=3){
                        board[i][j]=0;
                    }
                }else if (copy[i][j] ==0){
                    if (live>=3){
                        board[i][j]=1;
                    }
                }
            }
        }
    }

    private int countOfLiveNeighours(int[][] board, int i, int j){
        int m = board.length;
        int n = board[0].length;

        int[] live = new int[8];
        if (i>0 && j>0){
            live[0]=board[i-1][j-1];
        }
        if (i > 0) {
            live[1] = board[i-1][j];
        }
        if (i>0 && j<n-1){
            live[2] = board[i-1][j+1];
        }
        if (j>0){
            live[3] = board[i][j-1];
        }
        if (j<n-1){
            live[4] = board[i][j+1];
        }
        if (i<m-1 && j>0){
            live[5] = board[i+1][j-1];
        }
        if (i<m-1){
            live[6] = board[i+1][j];
        }
        if (i<m-1 && j<n-1){
            live[7] = board[i+1][j+1];
        }

        int count=0;
        for (int k=0;k<8;k++){
            count += live[k];
        }
        return count;
    }
}
