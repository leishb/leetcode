package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/11/5.
 */
public class _460_LFU_Cache {


    class Node {
        int key;
        int value;
        int freq = 0;
        int stamp = 0;

        Node (int key, int value){
            this.key = key;
            this.value = value;
        }
    }


    Map<Integer, Node> map ;
    Queue<Node> queue ;

    int stamp = 0;
    int capactiy = 0;


    public _460_LFU_Cache(int capacity) {
        map = new HashMap<>();
        queue = new PriorityQueue<>((n1,n2)->n1.freq==n2.freq?n1.stamp-n2.stamp:n1.freq-n2.freq);
        stamp = 0;
        this.capactiy = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        stamp++;
        Node node = map.get(key);
        queue.remove(node);
        node.freq +=1;
        node.stamp = stamp;
        queue.offer(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capactiy==0) return;
        stamp++;
        if (map.containsKey(key)){
            map.get(key).value = value;
            queue.remove(map.get(key));
        }else {
            if (map.size()>=capactiy){
                Node node = queue.poll();
                map.remove(node.key);
            }
            map.put(key, new Node(key, value));
        }
        map.get(key).freq +=1;
        map.get(key).stamp = stamp;
        queue.offer(map.get(key));
    }
}
