package com.leishb.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/7/19.
 */
public class _529_Minesweeper {

    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,1},{-1,1},{1,-1}};


    /**
     * Accepted
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        if (board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }
        int mines = getMines(board, i, j);
        if (mines>0){
            board[i][j] = (char)('0'+mines);
            return board;
        }
        boolean[][] visisted = new boolean[board.length][board[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        visisted[i][j] = true;
        while (!queue.isEmpty()){
            int[] q = queue.poll();
            i = q[0];
            j = q[1];
            board[i][j] = 'B';
            for (int[] dir : dirs){
                int x = i + dir[0];
                int y = j + dir[1];
                if (x>=0 && y>=0 && x<board.length && y<board[0].length && !visisted[x][y]){
                    visisted[x][y] = true;
                    int m = getMines(board ,x ,y);
                    if (m==0){
                        queue.offer(new int[]{x, y});
                    }else {
                        board[x][y] = (char)('0'+m);
                    }
                }
            }
        }
        return board;
    }


    private int getMines(char[][] board, int i, int j){
        int mines = 0;
        for (int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x>=0 && y>=0 && x<board.length && y<board[0].length){
                if (board[x][y] == 'M'){
                    mines+=1;
                }
            }
        }
        return mines;
    }
}
