package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/13.
 */
public class _792_Number_of_Matching_Subsequences {


    /**
     * Accepted
     * @param S
     * @param words
     * @return
     */
    public int numMatchingSubseq(String S, String[] words) {
        List<Integer>[] counts = new List[26];
        for (int i=0;i<26;i++){
            counts[i] = new ArrayList<>();
        }
        for (int i=0;i<S.length();i++){
            counts[S.charAt(i)-'a'].add(i);
        }
        int ans = 0;
        for (String word : words){
            if (isSubseq(counts, word)){
                ans++;
            }
        }
        return ans;
    }


    private boolean isSubseq(List<Integer>[] counts, String word){
        if (word.length()==0)return true;
        if (counts[word.charAt(0)-'a'].size()==0)return false;
        int pos = counts[word.charAt(0)-'a'].get(0);
        char[] cs = word.toCharArray();
        for (int i=1;i<cs.length;i++){
            boolean found = false;
            if (counts[cs[i]-'a'].size()>0 && counts[cs[i]-'a'].get(counts[cs[i]-'a'].size()-1) < pos){
                return false;
            }
            for (int j=0;j<counts[cs[i]-'a'].size();j++){
                if (counts[cs[i]-'a'].get(j) > pos){
                    found = true;
                    pos = counts[cs[i]-'a'].get(j);
                    break;
                }
            }
            if (!found){
                return false;
            }
        }
        return true;
    }

    /**
     * Accepted
     * @param S
     * @param words
     * @return
     */
    public int numMatchingSubseq2(String S, String[] words) {
        Map<Character, Queue<String>> map = new HashMap<>();
        for (char c = 'a';c<='z';c++){
            map.put(c, new LinkedList<>());
        }
        for (String word : words){
            map.get(word.charAt(0)).offer(word);
        }
        int ans = 0;
        char[] cs = S.toCharArray();
        for (int i=0;i<cs.length;i++){
            Queue<String> queue = map.get(cs[i]);
            int size = queue.size();
            for (int j=0;j<size;j++){
                String word = queue.poll();
                if (word.length()==1){
                    ans++;
                }else {
                    map.get(word.charAt(1)).offer(word.substring(1));
                }
            }
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(numMatchingSubseq2("abcde", new String[]{"a", "bb", "acd", "ace"})==3);
    }
}
