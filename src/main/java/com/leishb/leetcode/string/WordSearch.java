package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DFS;

/**
 * Created by me on 2019/3/8.
 */
@DFS
@BackTracking
public class WordSearch {

    private boolean found = false;


    /**
     * Accepted
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visisted = new boolean[board.length][board[0].length];
        for (int i=0;i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtracking(board, word,visisted , i, j, 0);
            }
        }
        return found;
    }


    public boolean exist(String s1, String s2) {
        for (int i=0;i<=s1.length()-s2.length()+1;i++){
            backtracking(s1, s2, i, 0);
        }
        return found;
    }

    private void backtracking(String s1, String s2, int i, int k){
        if (k==s2.length()){
            found = true;
            return;
        }
        if (found){
            return;
        }
        if (s1.charAt(i)!=s2.charAt(k)){
            return;
        }
        backtracking(s1, s2, i+1, k+1);
    }



    private void backtracking(char[][] board, String word, boolean[][] visisted, int i, int j, int k){
        if (k == word.length()){
            found = true;
            return;
        }
        if (found || k==word.length() || i<0 || j< 0 || i == board.length || j == board[0].length){
            return;
        }
        if (visisted[i][j]){
            return;
        }
        char c = word.charAt(k);
        if (c != board[i][j]){
            return;
        }
        visisted[i][j] = true;
        backtracking(board, word, visisted, i+1, j, k+1);
        backtracking(board, word, visisted, i, j+1, k+1);
        backtracking(board, word, visisted, i-1, j, k+1);
        backtracking(board, word, visisted, i, j-1, k+1);
        visisted[i][j] = false;
    }
}
