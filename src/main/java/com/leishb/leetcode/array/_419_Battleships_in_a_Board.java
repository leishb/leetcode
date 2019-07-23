package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/7/18.
 */
public class _419_Battleships_in_a_Board {


    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if (board[i][j] == 'X'){
                    count++;
                    travel(board, i, j);
                }
            }
        }
        return count;
    }

    private void travel(char[][] board, int i, int j){
        if (i<board.length-1 && board[i+1][j]=='X'){
            for (int p=i;p<board.length;p++){
                if (board[p][j]=='X'){
                    board[p][j] = '0';
                }else {
                    break;
                }
            }
        }
        if (j<board[0].length-1 && board[i][j+1]=='X'){
            for (int p=j;p<board[0].length;p++){
                if (board[i][p]=='X'){
                    board[i][p] = '0';
                }else {
                    break;
                }
            }
        }
    }


    @Test
    public void test(){
        Assert.assertTrue(countBattleships(new char[][]{
                {'.','X','.','.','X'},
                {'.','X','.','.','X'},
                {'.','.','.','.','X'},
                {'X','.','X','X','.'},
                {'X','.','.','.','X'}})==5);
    }
}
