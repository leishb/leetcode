package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/8/5.
 */
public class _692_Top_K_Frequent_Words {


    /**
     * Accepted
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words){
            map.putIfAbsent(word, 0);
            map.put(word, map.get(word)+1);
        }
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((e1,e2)->
                e1.getValue()!=e2.getValue()?(e2.getValue()-e1.getValue()):e1.getKey().compareTo(e2.getKey()));
        queue.addAll(map.entrySet());
        while (k>0){
            ans.add(queue.poll().getKey());
            k--;
        }
        return ans;
    }
}
