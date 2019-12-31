package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/12/31.
 */
public class _30_Substring_with_Concatenation_of_All_Words {


    /**
     * Accepted
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length==0) return res;
        int len = words[0].length();
        int[] wordCounts = new int[26];
        Arrays.sort(words);
        for (String w : words){
            for (char c : w.toCharArray()){
                wordCounts[c-'a']++;
            }
        }
        int[] counts = new int[26];
        for (int i=0;i<s.length();i++){
            counts[s.charAt(i)-'a']++;
            if (i>=len * words.length-1){
                if (i>=len * words.length){
                    counts[s.charAt(i-len * words.length)-'a']--;
                }
                if (Arrays.equals(wordCounts, counts)){
                    String[] ws = new String[words.length];
                    for (int j=0;j<ws.length;j++){
                        int start = i-len * words.length + 1 + j * len;
                        ws[j] = s.substring(start , start+len);
                    }
                    Arrays.sort(ws);
                    if (Arrays.equals(ws, words)){
                        res.add(i-len * words.length+1);
                    }
                }
            }
        }
        return res;
    }
}
