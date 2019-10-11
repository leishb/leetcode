package com.leishb.leetcode.design;

/**
 * Created by me on 2019/10/10.
 */
public class _348_Design_Tic_Tac_Toe {


    int[][] grid = null;

    /** Initialize your data structure here. */
    public _348_Design_Tic_Tac_Toe(int n) {
        grid = new int[n][n];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        grid[row][col] = player;
        boolean win = checkHorizon(row, col, player) || checkVertical(row, col, player) || checkRightDialog(row, col, player)
                || checkLeftDialog(row, col, player);
        return win?player:0;
    }


    private boolean checkHorizon(int row, int col, int player){
        int i = col;
        while (i>=0){
            if (grid[row][i]!=player) return false;
            i--;
        }
        i = col;
        while (i<grid.length){
            if (grid[row][i]!=player) return false;
            i++;
        }
        return true;
    }


    private boolean checkVertical(int row, int col, int player){
        int i = row;
        while (i>=0){
            if (grid[i][col]!=player) return false;
            i--;
        }
        i = row;
        while (i<grid.length){
            if (grid[i][col]!=player) return false;
            i++;
        }
        return true;
    }


    private boolean checkRightDialog(int row, int col, int player){
        if (row!=col) return false;
        int i = row;
        while (i>=0){
            if (grid[i][i]!=player) return false;
            i--;
        }
        i = row;
        while (i<grid.length){
            if (grid[i][i]!=player) return false;
            i++;
        }
        return true;
    }

    private boolean checkLeftDialog(int row, int col, int player){
        if (row+col!=grid.length-1) return false;
        int i = row;
        while (i>=0){
            if (grid[i][grid.length-1-i]!=player) return false;
            i--;
        }
        i = row;
        while (i<grid.length){
            if (grid[i][grid.length-1-i]!=player) return false;
            i++;
        }
        return true;
    }


    public boolean validTicTacToe(String[] board) {
        int xrows = 0, xcols = 0, orows = 0, ocols = 0, xdia = 0, odia = 0 , X=0, O=0;
        for (int i=0;i<3;i++){
            String s = board[i];
            if (s.charAt(0)==s.charAt(1) && s.charAt(1)==s.charAt(2) && s.charAt(0)!= ' ' ){
                if (s.charAt(0)=='X') xrows++;
                else orows++;
            }
            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i) && board[0].charAt(i) != ' '){
                if (board[0].charAt(i)=='X') xcols++;
                else ocols++;
            }
            if (board[0].charAt(0)==board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2) && board[0].charAt(0) != ' '){
                if (board[0].charAt(0)=='X') xdia++;
                else odia++;
            }
            if (board[0].charAt(2)==board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0) && board[0].charAt(2) != ' '){
                if (board[0].charAt(2)=='X') xdia++;
                else odia++;
            }
            for (int j=0;j<3;j++){
                if (s.charAt(j)=='X'){
                    X++;
                }
                if (s.charAt(j)=='O'){
                    O++;
                }
            }
        }
        if (xrows + orows >1 || xcols + ocols>1) return false;
        if (xrows+xcols+xdia>=1 && X==O) return false;
        if (orows+ocols+odia>=1 && X!=O) return false;
        return X==O || X-O==1;
    }

    public static void main(String[] args){
        _348_Design_Tic_Tac_Toe toe = new _348_Design_Tic_Tac_Toe(3);
        toe.move(0,0,1);
        toe.move(0,2,2);
        toe.move(2,2,1);

        toe.validTicTacToe(new String[]{"XXX","OOX","OOX"});
    }
}
