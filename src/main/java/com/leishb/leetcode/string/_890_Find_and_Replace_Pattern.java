package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/8/23.
 */
public class _890_Find_and_Replace_Pattern {

    /**
     * Accepted
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words){
            if (match(word, pattern)){
                result.add(word);
            }
        }
        return result;
    }


    private boolean match(String word, String pattern){
        if (word.length()!=pattern.length()){
            return false;
        }
        Map<Character, Character> m1 = new HashMap<>();
        Map<Character, Character> m2 = new HashMap<>();
        for (int i=0;i<word.length();i++){
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (m1.containsKey(w) && m1.get(w)!=p){
                return false;
            }
            if (m2.containsKey(p) && m2.get(p)!=w){
                return false;
            }
            m1.put(w, p);
            m2.put(p, w);
        }
        return true;
    }
}
