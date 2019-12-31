package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/12/6.
 */
public class LRUCache {

    int capacity ;
    DLinkedList linkedList;
    Map<Integer, Node> map ;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        linkedList = new DLinkedList();
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        linkedList.unlink(node);
        linkedList.addLast(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            this.get(key);
            return;
        }
        Node node = new Node(key, value);
        linkedList.addLast(node);
        map.put(key, node);
        if (map.size()>capacity){
            Node n = linkedList.removeFirst();
            map.remove(n.key);
        }
    }


    class Node {
        Node prev, next;
        int val;
        int key;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }


    class DLinkedList{
        Node head, tail;

        DLinkedList(){
            head = new Node(0,0);
            tail = new Node(0,0);
            tail.next = tail;
            tail.prev = head;
        }


        public Node addLast(Node node){
            Node prev = tail.prev;
            prev.next = node;
            node.prev = prev;
            node.next = tail;
            tail.prev = node;
            return node;
        }


        public Node removeFirst(){
            Node node = head.next;
            unlink(node);
            return node;
        }


        public void unlink(Node node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }
}
