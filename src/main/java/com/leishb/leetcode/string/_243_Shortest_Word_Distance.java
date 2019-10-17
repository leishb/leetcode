package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/10/16.
 */
public class _243_Shortest_Word_Distance {

    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i=0;i<words.length;i++){
            if (word1.equals(words[i])){
                l1.add(i);
            }
            if (word2.equals(words[i])){
                l2.add(i);
            }
        }
        int min = words.length;
        for (int i : l1){
            for (int j:l2){
                min = Math.min(min, Math.abs(i-j));
            }
        }
        return min;
    }


    Map<String, TreeSet<Integer>> map = new HashMap<>();


    public _243_Shortest_Word_Distance(String[] words) {
        for (int i=0;i<words.length;i++){
            TreeSet<Integer> set = map.getOrDefault(words[i], new TreeSet<>());
            set.add(i);
            map.put(words[i], set);
        }
    }

    public int shortest(String word1, String word2) {
        TreeSet<Integer> s1 = map.get(word1);
        TreeSet<Integer> s2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        for (int i : s1){
            Integer h = s2.higher(i);
            if (h!=null) min = Math.min(min, Math.abs(h-i));
            Integer l = s2.lower(i);
            if (l!=null) min = Math.min(min, Math.abs(l-i));
        }
        return min;
    }
}
