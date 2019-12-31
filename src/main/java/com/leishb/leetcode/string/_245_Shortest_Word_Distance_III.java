package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/11/25.
 */
public class _245_Shortest_Word_Distance_III {


    public int shortestWordDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i=0;i<words.length;i++){
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
        int min = words.length;
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        if (word1.equals(word2)){
            for (int i=1;i<l1.size();i++){
                min = Math.min(min, l1.get(i)-l1.get(i-1));
            }
            return min;
        }
        int i=0, j=0;
        while (i<l1.size() && j<l2.size()){
            min = Math.min(min, Math.abs(l1.get(i)-l2.get(j)));
            if (l1.get(i) < l2.get(j)){
                i++;
            }else {
                j++;
            }
        }
        return min;
    }

}
