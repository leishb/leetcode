package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/10/8.
 */
public class _37_Sudoku_Solver {

    public void solveSudoku(char[][] board) {
        Set<Character>[] rows = new Set[9];
        Set<Character>[] cols = new Set[9];
        Set<Character>[] dias = new Set[9];
        for (int i=0;i<9;i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            dias[i] = new HashSet<>();
        }
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (board[i][j]!='.'){
                    rows[i].add(board[i][j]);
                    cols[j].add(board[i][j]);
                    dias[i/3*3 + j/3].add(board[i][j]);
                }
            }
        }
        backtracking(board, 0, rows, cols, dias);
    }


    private boolean backtracking(char[][] grid, int index,  Set<Character>[] rows, Set<Character>[] cols, Set<Character>[] dias){
        if (index==81) return true;
        int row = index/9;
        int col = index%9;
        int dia = row/3*3+col/3;
        if (grid[row][col] != '.'){
            return backtracking(grid, index+1, rows, cols, dias);
        }
        for (char c = '1';c<='9';c++){
            if (rows[row].contains(c) || cols[col].contains(c) || dias[dia].contains(c)){
                continue;
            }
            rows[row].add(c);
            cols[col].add(c);
            dias[dia].add(c);
            grid[row][col] = c;
            if (backtracking(grid, index+1, rows, cols, dias)){
                return true;
            }
            rows[row].remove(c);
            cols[col].remove(c);
            dias[dia].remove(c);
            grid[row][col] = '.';
        }
        return false;
    }

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
}
