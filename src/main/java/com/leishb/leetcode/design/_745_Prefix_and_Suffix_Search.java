package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/12/11.
 */
public class _745_Prefix_and_Suffix_Search {



    Trie root;
    Map<String, Integer> map = new HashMap<>();
    public _745_Prefix_and_Suffix_Search(String[] words) {
        root = new Trie();
        for (int i=words.length-1;i>=0;i--){
            addWord(words[i], root);
            map.putIfAbsent(words[i], i);
        }
    }

    public int f(String prefix, String suffix) {
        Trie cur = root;
        for (int i=0;i<prefix.length();i++){
            int j = prefix.charAt(i)-'a';
            if (cur.children[j]==null) return -1;
            cur = cur.children[j];
        }
        for (String word : cur.words){
            if (word.endsWith(suffix)){
                return map.get(word);
            }
        }
        return -1;
    }


    private void addWord(String word, Trie root){
        Trie cur = root;
        cur.words.add(word);
        for (int i=0;i<word.length();i++){
            int j = word.charAt(i)-'a';
            if (cur.children[j]==null){
                cur.children[j] = new Trie();
            }
            cur = cur.children[j];
            cur.words.add(word);
        }
    }

    class Trie {
        Trie[] children = new Trie[26];
        List<String> words = new ArrayList<>();
    }
}
