package com.leishb.leetcode.design;

import java.util.TreeMap;

/**
 * Created by me on 2019/10/17.
 */
public class _362_Design_Hit_Counter {

    TreeMap<Integer, Integer> prefix = new TreeMap<>();

    int prev = 0;

    /** Initialize your data structure here. */
    public _362_Design_Hit_Counter() {
        prefix.put(0, 0);
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        prefix.put(timestamp, prefix.getOrDefault(timestamp, prefix.get(prev))+1);
        prev = timestamp;
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        Integer k = prefix.lowerKey(timestamp-300+1);
        if (k!=null) return prefix.getOrDefault(timestamp, prefix.get(prev))- prefix.get(k);
        return prefix.getOrDefault(timestamp, prefix.get(prev));
    }

    public static void main(String[] args){
        _362_Design_Hit_Counter counter = new _362_Design_Hit_Counter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        counter.hit(300);
        counter.getHits(4);
    }
}
