package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/8.
 */
public class _212_Word_Search_II {


    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String word : words){
            root = buildTrieTree(word, root);
        }
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                dfs(board, i, j, root, ans);
            }
        }
        return ans;
    }


    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> ans){
        char c = board[i][j];
        if (c == '#' || p.children[c-'a']==null) return;
        p = p.children[c-'a'];
        if (p.word!=null){
            ans.add(p.word);
            p.word = null;
        }
        board[i][j] = '#';
        if (i>0) dfs(board, i-1, j, p, ans);
        if (j>0) dfs(board, i, j-1, p, ans);
        if (i<board.length-1) dfs(board, i+1, j, p, ans);
        if (j<board[0].length-1) dfs(board, i, j+1, p, ans);
        board[i][j] = c;
    }


    private TrieNode buildTrieTree(String word, TrieNode root){
        int i = 0;
        TrieNode cur = root;
        while (i<word.length()){
            char c = word.charAt(i);
            if (cur.children[c-'a']==null){
                cur.children[c-'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
            i++;
        }
        cur.word = word;
        return root;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;

    }
}
