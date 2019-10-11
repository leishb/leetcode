package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/8.
 */
public class _358_Rearrange_String_k_Distance_Apart {

    public String rearrangeString(String s, int k) {
        if (k==0) return s;
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2)->e2.getValue()-e1.getValue());
        pq.addAll(freq.entrySet());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            sb.append(entry.getKey());
            entry.setValue(entry.getValue()-1);
            queue.offer(entry);
            if (queue.size() < k){
                continue;
            }
            Map.Entry<Character, Integer> e = queue.poll();
            if (e.getValue() > 0) pq.offer(e);
        }
        return sb.length()==s.length() ? sb.toString() : "";
    }

    @Test
    public void test(){
//        rearrangeString("abccba", 2);
        StringBuffer sb = new StringBuffer();
        for (char c = 'a'; c<='z';c++){
            for (int i=0;i<500;i++){
                sb.append(c);
            }
        }
        rearrangeString(sb.toString(), 26);
    }
}
