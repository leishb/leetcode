package com.leishb.leetcode.design;

/**
 * Created by me on 2019/12/11.
 */
public class WordFilter {



    Trie root;

    public WordFilter(String[] words) {
        root = new Trie();
        for (int i=0;i<words.length;i++){
            for (int j=0;j<=words[i].length();j++){
                addWord(words[i].substring(j, words[i].length()) + "{" + words[i], i);
            }
        }
    }



    public int f(String prefix, String suffix) {
        return search(suffix+"{"+prefix);
    }



    private void addWord(String word , int weight){
        Trie cur = root;
        cur.weight = weight;
        for (int i=0;i<word.length();i++){
            int j = word.charAt(i)-'a';
            if (cur.children[j]==null){
                cur.children[j] = new Trie();
            }
            cur = cur.children[j];
            cur.weight = weight;
        }
    }


    private int search(String prefix){
        Trie cur = root;
        for (int i=0;i<prefix.length();i++){
            int j = prefix.charAt(i)-'a';
            if (cur.children[j]==null) return -1;
            cur = cur.children[j];
        }
        return cur.weight;
    }


    class Trie {
        Trie[] children = new Trie[27];
        int weight;
    }
}
