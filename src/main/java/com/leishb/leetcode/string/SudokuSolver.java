package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2017/12/12.
 */
public class SudokuSolver {


    @Test
    public void test(){
        char[][] board = new char[][]{
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };
        long start = System.currentTimeMillis();
        solveSudoku(board);
        System.out.println("time : " + (System.currentTimeMillis() - start));
    }



    public void solveSudoku(char[][] board) {
        backtracking(board, 0, 0);
    }



    private boolean backtracking(char[][] board, int row, int col){
        if(col == 9){
            col = 0;
            row += 1;
        }
        if(row==9){
            return true;
        }
        if(board[row][col] != '.'){
            return backtracking(board,  row, col+1);
        }
        for(char c = '1'; c<='9'; c+=1){
            if (isValidSudoku(board, row, col)) {
                board[row][col]=c;
                if (backtracking(board, row, col+1)){
                    return true;
                }
                board[row][col]='.';
            }
        }
        return false;
    }



    public boolean isValidSudoku(char[][] board, int row, int col) {
        for(int i=0;i<=row; i++){
            Set<Character> rowSet = new HashSet();
            Set<Character> colSet = new HashSet();
            for(int j=0;j<=col; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                if(rowSet.contains(board[i][j])){
                    return false;
                }
                rowSet.add(board[i][j]);

                if(board[j][i] == '.'){
                    continue;
                }
                if(colSet.contains(board[j][i])){
                    return false;
                }
                colSet.add(board[j][i]);
            }
        }

        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                Set<Character> set = new HashSet();
                for(int p=(i-1)*3 ; p< i*3 && p<=row ; p++){
                    for(int q=(j-1)*3 ; q< j*3 && q<=col ; q++){
                        if(board[p][q] == '.'){
                            continue;
                        }
                        if(set.contains(board[p][q])){
                            return false;
                        }
                        set.add(board[p][q]);
                    }
                }
            }
        }
        return true;
    }
}
