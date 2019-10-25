package com.leishb.leetcode.dp;

import java.util.*;

/**
 * Created by me on 2019/10/22.
 */
public class _472_Concatenated_Words {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String w : words){
            set.remove(w);
            if (dfs(w, set, "")){
                ans.add(w);
            }
            set.add(w);
        }
        return ans;
    }



    private boolean dfs(String s, Set<String> set, String prev){
        if (!"".equals(prev)){
            set.add(prev);
        }
        if (set.contains(s)) return true;
        for (int i=1;i<s.length();i++){
            String prefix = s.substring(0, i);
            if (set.contains(prefix) && dfs(s.substring(i), set, prev+prefix)){
                return true;
            }
        }
        return false;
    }


    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        String word = null;
    }


    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words){
            addWord(root, w);
        }
        List<String> ans = new ArrayList<>();
        for (String w : words){
            if (search(w.toCharArray(), root, 0)){
                ans.add(w);
            }
        }
        return ans;
    }


    private void addWord(TrieNode root, String word){
        TrieNode cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.children[c-'a']==null){
                cur.children[c-'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
        }
        cur.isEnd = true;
        cur.word = word;
    }


    private boolean search(char[] chars, TrieNode root, int index){
        int n = chars.length;
        TrieNode cur = root;
        for (int i= index;i<n;i++){
            cur = cur.children[chars[i]-'a'];
            if (cur==null) return false;
            if (cur.isEnd){
                if (i==n-1){
                    return !new String(chars).equals(cur.word);
                }
                if (search(chars, root, i+1)){
                    return true;
                }
            }
        }
        return false;
    }
}
