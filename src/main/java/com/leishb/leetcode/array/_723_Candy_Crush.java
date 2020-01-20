package com.leishb.leetcode.array;

/**
 * Created by me on 2020/1/17.
 */
public class _723_Candy_Crush {


    /**
     * Accepted
     * @param board
     * @return
     */
    public int[][] candyCrush(int[][] board) {
        int R = board.length, C = board[0].length;
        boolean todo = false;
        for (int r=0;r<R;r++){
            for (int c=0;c+2<C;c++){
                int v = Math.abs(board[r][c]);
                if (v!=0 && Math.abs(board[r][c+1])==v && Math.abs(board[r][c+2])==v){
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    todo = true;
                }

            }
        }
        for (int r=0;r+2<R;r++){
            for (int c=0;c<C;c++){
                int v = Math.abs(board[r][c]);
                if (v!=0 && Math.abs(board[r+1][c])==v && Math.abs(board[r+2][c])==v){
                    board[r][c] = board[r+1][c] = board[r+2][c] = -v;
                    todo = true;
                }
            }
        }
        if (!todo) return board;
        for (int c=0;c<C;c++){
            int i = R-1;
            for (int r=R-1;r>=0;r--){
                if (board[r][c] > 0){
                    board[i--][c] = board[r][c];
                }
            }
            while (i>=0){
                board[i--][c] = 0;
            }
        }
        return candyCrush(board);
    }
}
