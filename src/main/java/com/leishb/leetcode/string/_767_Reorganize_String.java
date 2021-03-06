package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/7/23.
 */
public class _767_Reorganize_String {


    /**
     * Accepted
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        Queue<Map.Entry<Character, Integer>> queue = new PriorityQueue(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getValue()==o2.getValue()?Character.compare(o1.getKey(), o2.getKey()):Integer.compare(o2.getValue(), o1.getValue());
            }
        });
        queue.addAll(map.entrySet());
        String ans = "";
        while (!queue.isEmpty()){
            int k = 2;
            List<Map.Entry> tempList = new ArrayList<>();
            while (k>0 && !queue.isEmpty()){
                Map.Entry<Character, Integer> entry = queue.poll();
                entry.setValue(entry.getValue()-1);
                k--;
                ans = ans +entry.getKey();
                tempList.add(entry);
            }
            for (Map.Entry<Character, Integer> entry : tempList){
                if (entry.getValue()!=0){
                    queue.offer(entry);
                }
            }
            if (queue.isEmpty()){
                break;
            }
            if (k>0){
                return "";
            }
        }
        return ans;
    }


    public String reorganizeString2(String S) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : S.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2)->e2.getValue()-e1.getValue());
        pq.addAll(freq.entrySet());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            entry.setValue(entry.getValue()-1);
            queue.offer(entry);
            sb.append(entry.getKey());
            if (queue.size()<2){
                continue;
            }
            Map.Entry<Character, Integer> front = queue.poll();
            if (front.getValue() > 0) pq.offer(front);
        }
        return sb.length()==S.length()?sb.toString():"";
    }
}
