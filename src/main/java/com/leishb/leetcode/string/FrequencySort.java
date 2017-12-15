package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2017/12/12.
 */
public class FrequencySort {


    /**
     * Accepted
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Item> list = new ArrayList();
        for(Character c : map.keySet()){
            list.add(new Item(c, map.get(c)));
        }
        Collections.sort(list, (i1, i2) -> i2.freq-i1.freq);
        StringBuffer sb = new StringBuffer();
        for(Item item : list){
            for(int i=0;i<item.freq;i++){
                sb.append(item.c);
            }
        }
        return sb.toString();
    }

    class Item{
        char c;
        int freq;

        Item(char c, int freq){
            this.c = c;
            this.freq = freq;
        }
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        String[] bucket = new String[s.length()+1];
        for(Character c : map.keySet()){
            bucket[map.get(c)] = helper(bucket[map.get(c)], c, map.get(c));
        }
        StringBuffer sb = new StringBuffer();
        for(int i=bucket.length-1; i>0;i--){
            if(bucket[i]!=null){
                sb.append(bucket[i]);
            }
        }
        return sb.toString();
    }


    private String helper(String s, char c , int freq){
        StringBuffer sb = new StringBuffer();
        if(s!=null){
            sb.append(s);
        }
        for(int i=0;i<freq;i++){
            sb.append(c);
        }
        return sb.toString();
    }




    /**
     * Accepted
     * @param s
     * @return
     */
    public String frequencySort3(String s) {
        Map<Character, Integer> map = new HashMap();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());
        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()){
            Map.Entry<Character, Integer> entry = queue.poll();
            for (int i=0;i<entry.getValue();i++){
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}
