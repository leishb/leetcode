package com.leishb.leetcode.design;

import org.junit.Test;

/**
 * Created by me on 2019/6/14.
 */
public class WordDictionary {

    class TrieTree{
        char data;
        boolean isWord = false;
        TrieTree[] children;

        TrieTree(char data){
            this.data = data;
            children = new TrieTree[26];
        }
    }

    TrieTree root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieTree('/');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] cs = word.toCharArray();
        TrieTree node = root;
        for (int i=0;i<cs.length;i++){
            TrieTree cur = node.children[cs[i]-'a'];
            if (cur==null){
                cur = new TrieTree(cs[i]);
            }
            node.children[cs[i]-'a'] = cur;
            node = cur;
        }
        node.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] cs = word.toCharArray();
        return dfs(root, cs, 0);
    }


    private boolean dfs(TrieTree node, char[] cs, int index){
        if (index==cs.length){
            return node.isWord;
        }
        for (TrieTree child : node.children){
            if (child==null || (cs[index]!='.' && cs[index]!=child.data)){
                continue;
            }
            if (dfs(child, cs, index+1)){
                return true;
            }
        }
        return false;
    }


    @Test
    public void test(){
        this.addWord("bad");
        this.addWord("dad");
        this.addWord("mad");

        search("bad");
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
