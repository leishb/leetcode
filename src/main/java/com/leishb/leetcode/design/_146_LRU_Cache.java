package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/21.
 */
public class _146_LRU_Cache {

    Map<Integer, ListNode> cache = new HashMap<>();
    int size = 0;
    int capacity = 0;
    ListNode head = null;
    ListNode tail = null;


    public _146_LRU_Cache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        ListNode node = cache.get(key);
        if (node==null) return -1;
        remove(node);
        addLast(node);
        return node.value;
    }

    public void put(int key, int value) {
        ListNode node = new ListNode(key, value);
        if (cache.containsKey(key)){
            node = cache.get(key);
            node.value = value;
            remove(node);
        }
        addLast(node);
        cache.put(key, node);
        while (size > capacity){
            ListNode cur = removeFirst();
            cache.remove(cur.key);
        }
    }


    class ListNode {
        ListNode next;
        ListNode prev;
        int key;
        int value;
        ListNode (int key, int value){
            this.key = key;
            this.value = value;
        }
    }


    private ListNode addLast(ListNode node){
        if (head==null){
            head = node;
            tail = head;
        }else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }
        size++;
        return node;
    }

    private void remove(ListNode node){
        if (node==head && node==tail){
            head = null;
            tail = null;
        }else if (node==tail){
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }else if (node==head){
            head = head.next;
            head.prev = null;
            node.next = null;
        }else {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
            node.prev = null;
            node.next = null;
        }
        size--;
    }


    private ListNode removeFirst(){
        if (head==null)return null;
        ListNode cur = head;
        if (head==tail){
            head=null;
            tail=null;
        }else {
            head = head.next;
            head.prev = null;
            cur.next = null;
        }
        size--;
        return cur;
    }
}
