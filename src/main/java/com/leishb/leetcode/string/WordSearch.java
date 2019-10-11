package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DFS;

import java.util.ArrayList;
import java.util.List;

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


    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        List<int[]>[] lists = new List[26];
        for (int i=0;i<26;i++){
            lists[i] = new ArrayList<>();
        }
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                lists[board[i][j]-'a'].add(new int[]{i, j});
            }
        }
        for (String word : words){
            for (int[] cur : lists[word.charAt(0)-'a']){
                if (dfs(board, cur[0], cur[1], 0, word, new boolean[board.length][board[0].length])){
                    ans.add(word);
                    break;
                }
            }
        }
        return ans;
    }

    int[][] dirs = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};

    private boolean dfs(char[][] board, int i, int j,  int k, String word, boolean[][] visited){
        if (k==word.length()){
            return true;
        }
        if (i<0 || j< 0 || i == board.length || j == board[0].length || visited[i][j] || word.charAt(k)!=board[i][j]){
            return false;
        }
        visited[i][j] = true;
        for (int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (dfs(board, x, y, k+1, word, visited)){
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}
