package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by me on 2019/11/5.
 */
public class _460_LFU_Cache_2 {


    Map<Integer, Integer> values;
    Map<Integer, Integer> counts;
    Map<Integer, LinkedHashSet<Integer>> countList;
    int capacity = 0;
    int min = -1;
    public _460_LFU_Cache_2(int capacity) {
        values = new HashMap<>();
        counts = new HashMap<>();
        countList = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) return -1;
        int count = counts.get(key);
        counts.put(key, counts.getOrDefault(key, 0)+1);
        countList.get(count).remove(key);
        if (count==min && countList.get(count).size()==0){
            min+=1;
        }
        countList.putIfAbsent(count+1, new LinkedHashSet<>());
        countList.get(count+1).add(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity<=0) return;
        if (values.containsKey(key)){
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >=capacity){
            int evit = countList.get(min).iterator().next();
            countList.get(min).remove(evit);
            if (countList.get(min).size()==0){
                countList.remove(min);
            }
            values.remove(evit);
            counts.remove(evit);
        }
        counts.put(key, 1);
        values.put(key, value);
        countList.putIfAbsent(1, new LinkedHashSet<>());
        countList.get(1).add(key);
        min = 1;
    }
}
