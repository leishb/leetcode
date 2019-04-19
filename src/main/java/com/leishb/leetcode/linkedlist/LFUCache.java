package com.leishb.leetcode.linkedlist;

import java.util.*;

/**
 * Created by me on 2019/4/10.
 * https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {

    class Node{

        private int key;
        private int val;
        private int freq;

        Node(int key, int val){
            this.key = key;
            this.val = val;
            freq = 0;
        }
    }

    private Map<Integer, Node> cache;

    private TreeMap<Integer, LinkedList<Node>> freqMap;

    private int CAP;


    public LFUCache(int capacity) {
        this.CAP = capacity;
        cache = new HashMap<>();
        freqMap = new TreeMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)){
            return -1;
        }
        Node node = cache.get(key);
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (CAP==0){
            return;
        }
        if (cache.containsKey(key)){
            cache.get(key).val = value;
        }else if (cache.size() == CAP){
            int minFreq = freqMap.firstKey();
            Node n = freqMap.get(minFreq).removeFirst();
            if (freqMap.get(minFreq).size()==0){
                freqMap.remove(minFreq);
            }
            cache.remove(n.key);
        } else {
            cache.put(key, new Node(key, value));
        }
        Node node = cache.get(key);
        updateFreq(node);
    }


    private void updateFreq(Node node){
        if (freqMap.containsKey(node.freq)){
            freqMap.get(node.freq).remove(node);
            if (freqMap.get(node.freq).size()==0){
                freqMap.remove(node.freq);
            }
        }
        node.freq += 1;
        if (!freqMap.containsKey(node.freq)){
            freqMap.put(node.freq, new LinkedList<>());
        }
        freqMap.get(node.freq).add(node);
    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
