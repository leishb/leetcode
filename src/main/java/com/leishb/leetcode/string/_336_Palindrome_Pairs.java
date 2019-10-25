package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/23.
 */
public class _336_Palindrome_Pairs {


    /**
     * Accepted
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        TrieNode root = new TrieNode();
        TrieNode reverseRoot = new TrieNode();
        for (int i=0;i<words.length;i++){
            addWord(words[i], root, i);
            addWord(new StringBuffer(words[i]).reverse().toString(), reverseRoot, i);
        }
        Set<String> seen = new HashSet<>();
        for (int i=0;i<words.length;i++){
            String reverse = new StringBuffer(words[i]).reverse().toString();
            List<Integer> list = search(reverse, root);
            for (int id : list){
                if (id==i || seen.contains(id + ","+i)) continue;
                if (valid(words[id].substring(words[i].length()))){
                    ans.add(Arrays.asList(id, i));
                    seen.add(id + ","+i);
                }
            }
            List<Integer> l2 = search(words[i], reverseRoot);
            for (int id : l2){
                if (id==i || seen.contains(i+"," +id)) continue;
                if (valid(new StringBuffer(words[id]).reverse().substring(words[i].length()))){
                    ans.add(Arrays.asList(i, id));
                    seen.add(i+"," +id);
                }
            }
        }
        return ans;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        String word = null;
        List<Integer> ids = new ArrayList<>();
    }

    private void addWord(String w, TrieNode root, int index){
        TrieNode cur = root;
        cur.ids.add(index);
        for (int i=0;i<w.length();i++){
            if(cur.children[w.charAt(i)-'a']==null){
                cur.children[w.charAt(i)-'a'] = new TrieNode();
            }
            cur = cur.children[w.charAt(i)-'a'];
            cur.ids.add(index);
        }
        cur.isEnd = true;
        cur.word = w;
    }


    private List<Integer> search(String prefix, TrieNode root){
        TrieNode cur = root;
        for (int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            cur = cur.children[c-'a'];
            if (cur==null){
                return new ArrayList<>();
            }
        }
        return cur.ids;
    }


    private boolean valid(String s){
        int i=0;
        int j=s.length()-1;
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
