package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/11/4.
 */
public class _432_All_Oone_Data_Structure {


    class Node {
        String key;
        int value;


        Node(String key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Map<String, Node> map ;
    Queue<Node> max;
    Queue<Node> min;

    /** Initialize your data structure here. */
    public _432_All_Oone_Data_Structure() {
        map = new HashMap<>();
        max = new PriorityQueue<>((n1, n2)->n2.value-n1.value);
        min = new PriorityQueue<>((n1, n2)->n1.value-n2.value);
    }

    /** Inserts a new key <Key> with count 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)){
            max.remove(map.get(key));
            min.remove(map.get(key));
            map.get(key).value+=1;
        }else {
            map.put(key, new Node(key, 1));
        }
        max.offer(map.get(key));
        min.offer(map.get(key));
    }

    /** Decrements an existing key by 1. If Key's count is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) return;
        max.remove(map.get(key));
        min.remove(map.get(key));
        map.get(key).value-=1;
        if (map.get(key).value==0){
            map.remove(key);
        }else {
            max.offer(map.get(key));
            min.offer(map.get(key));
        }
    }

    /** Returns one of the keys with maximal count. */
    public String getMaxKey() {
        return max.isEmpty()?"":max.peek().key;
    }

    /** Returns one of the keys with Minimal count. */
    public String getMinKey() {
        return min.isEmpty()?"":min.peek().key;
    }
}
