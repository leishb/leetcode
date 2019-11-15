package com.leishb.leetcode.design;

/**
 * Accepted
 * Created by me on 2019/11/6.
 */
public class _1032_Stream_of_Characters {


    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        char data;
        boolean isWord = false;
        TrieNode(char data){
            this.data = data;
        }
    }

    TrieNode root = null;
    StringBuffer sb ;
    int maxLen = 0;

    public _1032_Stream_of_Characters(String[] words) {
        root = new TrieNode('/');
        sb = new StringBuffer();
        for (String word : words){
            maxLen = Math.max(maxLen, word.length());
            addWord(word, root);
        }
    }

    private void addWord(String word , TrieNode root){
        TrieNode cur = root;
        for (int i=word.length()-1;i>=0;i--){
            char c = word.charAt(i);
            if (cur.children[c-'a']==null){
                cur.children[c-'a'] = new TrieNode(c);
            }
            cur = cur.children[c-'a'];
        }
        cur.isWord = true;
    }

    public boolean query(char letter) {
        sb.append(letter);
        if (sb.length() > maxLen){
            sb.deleteCharAt(0);
        }
        TrieNode cur = root;
        for (int i=sb.length()-1;i>=0;i--){
            if (cur.children[sb.charAt(i)-'a']==null){
                return false;
            }
            if (cur.children[sb.charAt(i)-'a'].isWord) return true;
            cur = cur.children[sb.charAt(i)-'a'];
        }
        return false;
    }
}
