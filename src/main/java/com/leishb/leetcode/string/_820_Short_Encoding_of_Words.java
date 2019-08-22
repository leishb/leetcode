package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/16.
 */
public class _820_Short_Encoding_of_Words {

    /**
     * Accepted
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        for (int i=0;i<words.length;i++){
            words[i] = reverse(words[i]);
        }
        Arrays.sort(words, (s1, s2)->s2.length()-s1.length());
        TrieNode root = new TrieNode('/');
        int ans = 0;
        for (String word : words){
            if (word.length()!=0 && !containsWord(root, word)){
                ans += word.length()+1;
            }
        }
        return ans;
    }

    class TrieNode {
        char c ;
        TrieNode[] children = new TrieNode[26];
        boolean hasNext = false;
        int depth = 0;
        TrieNode(char c){
            this.c = c;
        }
    }

    private boolean containsWord(TrieNode root, String word){
        TrieNode cur = root;
        int i=0;
        while (i<word.length()){
            if (cur.children[word.charAt(i)-'a']==null){
                cur.children[word.charAt(i)-'a'] = new TrieNode(word.charAt(i));
            }else {
                if (i==word.length()-1){
                    return true;
                }
            }
            cur = cur.children[word.charAt(i)-'a'];
            i++;
        }
        return false;
    }

    private String reverse(String s){
        int i = 0;
        int j = s.length()-1;
        char[] cs = s.toCharArray();
        while (i<j){
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
            i++;
            j--;
        }
        return new String(cs);
    }


    public int minimumLengthEncoding2(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words){
            for (int i=1;i<word.length();i++){
                set.remove(word.substring(i));
            }
        }
        int ans = 0;
        for (String w : set){
            ans += w.length()+1;
        }
        return ans;
    }



    public int minimumLengthEncoding3(String[] words) {
        TrieNode root = new TrieNode('/');
        List<TrieNode> leaves = new ArrayList<>();
        for (String word : new HashSet<>(Arrays.asList(words))){
            TrieNode cur = root;
            for (int i=word.length()-1;i>=0;i--){
                char c = word.charAt(i);
                if (cur.children[c-'a']==null){
                    cur.children[c-'a']=new TrieNode(c);
                }
                cur.hasNext = true;
                cur = cur.children[c-'a'];
            }
            cur.depth = word.length();
            leaves.add(cur);
        }
        int ans = 0;
        for (TrieNode node : leaves){
            if (!node.hasNext){
                ans +=node.depth+1;
            }
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(minimumLengthEncoding3(new String[]{"time", "me", "bell"})==10);
        Assert.assertTrue(minimumLengthEncoding3(new String[]{"e","time", "me", "bell"})==10);
        Assert.assertTrue(minimumLengthEncoding3(new String[]{"time", "me", "bell", "ell"})==10);
        Assert.assertTrue(minimumLengthEncoding3(new String[]{"time", "me",  "ell", "bell"})==10);
        Assert.assertTrue(minimumLengthEncoding3(new String[]{"time", "atime",   "btime"})==12);
    }
}
