package com.leishb.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/4/9.
 */
public class LRUCache {

    private int capacity;

    private Map<Integer, ListNode> map;

    private LinkedList linkedList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        linkedList = new LinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key);
        linkedList.remove(node);
        linkedList.addLast(node);
        return node.val;
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if (node==null){
            map.put(key, new ListNode(key, value));
            linkedList.addLast(map.get(key));
        }else {
            linkedList.remove(node);
            linkedList.addLast(node);
            node.key = key;
            node.val = value;
        }
        if (map.size() > capacity){
            int k = linkedList.removeFirst();
            map.remove(k);
        }
    }


    class ListNode {
        ListNode prev;
        ListNode next;
        int key;
        int val;

        ListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    class LinkedList {

        private ListNode head;

        private ListNode tail;

        public void remove(ListNode node){
            if (node.prev!=null){
                node.prev.next = node.next;
            }else {
                head = node.next;
            }
            if (node.next!=null){
                node.next.prev = node.prev;
            }else {
                tail = node.prev;
            }
            node.next = null;
            node.prev = null;
        }


        public void addLast(ListNode node){
            if (tail==null){
                head = tail = node;
                return;
            }
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        public int removeFirst(){
            if (head==null){
                return -1;
            }
            ListNode h = head;
            ListNode next = h.next;
            h.next = null;
            head = next;
            if (head!=null){
                head.prev = null;
            }
            return h.key;
        }


        @Override
        public String toString(){
            ListNode cur = head;
            StringBuffer sb = new StringBuffer();
            while (cur != null){
                sb.append(cur.key).append("->");
                cur = cur.next;
            }
            return sb.toString();
        }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        System.out.println(cache.get(3));       // returns 1
        System.out.println(cache.get(2));       // returns 1
        cache.put(4, 3);    // evicts key 2
        System.out.println(cache.get(2));// returns -1 (not found)
        System.out.println(cache.get(3));      // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
