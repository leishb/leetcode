package com.leishb.leetcode.trie;

/**
 * Created by me on 2019/10/11.
 */
public class _676_Magic_Dictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }


    TrieNode root;

    public _676_Magic_Dictionary() {
        root = new TrieNode();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word: dict){
            TrieNode cur = root;
            for (int i=0;i<word.length();i++){
                char c = word.charAt(i);
                if (cur.children[c-'a'] == null){
                    cur.children[c-'a'] = new TrieNode();
                }
                cur = cur.children[c-'a'];
            }
            cur.isWord = true;
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return dfs(root, word, 0, false);
    }


    private boolean dfs(TrieNode root, String word, int index, boolean modified){
        if (index==word.length()){
            if (!modified) return false;
            return root.isWord;
        }
        char c = word.charAt(index);
        TrieNode cur = root;
        for (int i=0;i<cur.children.length;i++){
            TrieNode child = cur.children[i];
            if (child==null)continue;
            if (modified && i!=c-'a')continue;
            if (dfs(child, word, index+1, modified || i!=c-'a')){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){
        _676_Magic_Dictionary dic = new _676_Magic_Dictionary();
        dic.buildDict(new String[]{"hello","leetcode"});
        dic.search("hhllo");
        dic.search("leetcode");
    }
}
