package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/10/23.
 */
public class _336_Palindrome_Pairs_2 {


    /**
     * Accepted
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i=0;i<words.length;i++){
            addWord(words, i, root);
        }
        for (int i=0;i<words.length;i++){
            search(words, i, root, ans);
        }
        return ans;
    }


    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int index = -1;
        List<Integer> ids = new ArrayList<>();
    }

    private void addWord(String[] words, int index, TrieNode root){
        String w = words[index];
        TrieNode cur = root;
        for (int i=w.length()-1;i>=0;i--){
            if (isPalindrom(w, 0, i)){
                cur.ids.add(index);
            }
            if (cur.next[w.charAt(i)-'a']==null){
                cur.next[w.charAt(i)-'a'] = new TrieNode();
            }
            cur =  cur.next[w.charAt(i)-'a'];
        }
        cur.index = index;
        cur.ids.add(index);
    }

    private void search(String[] words, int index, TrieNode root, List<List<Integer>> ans){
        TrieNode cur = root;
        String word = words[index];
        for (int i=0;i<word.length();i++){
            if (cur.index>=0 && cur.index!=index && isPalindrom(word, i, word.length()-1)){
                ans.add(Arrays.asList(index, cur.index));
            }
            cur =  cur.next[word.charAt(i)-'a'];
            if (cur==null) return;
        }
        for (int i : cur.ids){
            if (i==index) continue;
            ans.add(Arrays.asList(index, i));
        }
    }

    private boolean isPalindrom(String s, int i, int j){
        while (i<j){
            if (s.charAt(i++)!=s.charAt(j--)) return false;
        }
        return true;
    }

    @Test
    public void test(){
        System.out.println(palindromePairs(new String[]{"ab","ba","abc","cba"}));
        System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
}
