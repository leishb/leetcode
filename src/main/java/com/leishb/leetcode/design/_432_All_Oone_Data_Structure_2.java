package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/4.
 */
public class _432_All_Oone_Data_Structure_2 {


    class Node {
        String key;
        int value;
        Node prev = null;
        Node next = null;

        Node(String key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    Map<String, Node> map;

    /** Initialize your data structure here. */
    public _432_All_Oone_Data_Structure_2() {
        map = new HashMap<>();
        head = new Node("", Integer.MIN_VALUE);
        tail = new Node("", Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    /** Inserts a new key <Key> with count 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)){
            map.put(key, new Node(key, 1));
            link(head, map.get(key));
        }else {
            map.get(key).value+=1;
            moveBack(map.get(key));
        }
    }

    /** Decrements an existing key by 1. If Key's count is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) return;
        map.get(key).value-=1;
        if (map.get(key).value > 0){
            moveFront(map.get(key));
        }else {
            unlink(map.get(key));
            map.remove(key);
        }
    }

    /** Returns one of the keys with maximal count. */
    public String getMaxKey() {
        return map.isEmpty()?"":tail.prev.key;
    }

    /** Returns one of the keys with Minimal count. */
    public String getMinKey() {
        return map.isEmpty()?"":head.next.key;
    }


    private void link(Node prev, Node cur){
        Node next = prev.next;
        prev.next = cur;
        cur.next = next;
        next.prev = cur;
        cur.prev = prev;
    }

    private void unlink(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void moveBack(Node node){
        Node cur = node.next;
        unlink(node);
        while (cur.value < node.value){
            cur = cur.next;
        }
        link(cur.prev, node);
    }

    private void moveFront(Node node){
        Node cur = node.prev;
        unlink(node);
        while (cur.value > node.value){
            cur = cur.prev;
        }
        link(cur, node);
    }
}
