package com.leishb.leetcode.design;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by me on 2019/12/6.
 */
public class _716_Max_Stack_2 {

    DLinkedList linkedList;
    TreeMap<Integer, List<Node>> map;

    /** initialize your data structure here. */
    public _716_Max_Stack_2() {
        linkedList = new DLinkedList();
        map = new TreeMap<>();
    }

    public void push(int x) {
        Node n = linkedList.add(x);
        map.putIfAbsent(x, new LinkedList<>());
        map.get(x).add(n);
    }

    public int pop() {
        Node node = linkedList.removeLast();
        List<Node> list = map.get(node.val);
        list.remove(list.size()-1);
        if (map.get(node.val).isEmpty()) map.remove(node.val);
        return node.val;
    }

    public int top() {
        return linkedList.getLast().val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = map.lastKey();
        List<Node> list = map.get(max);
        Node node = list.get(list.size()-1);
        linkedList.remove(node);
        list.remove(list.size()-1);
        if (map.get(max).isEmpty()) map.remove(max);
        return max;
    }


    class Node {
        int val;
        Node prev, next;
        Node(int val){
            this.val = val;
        }
    }


    class DLinkedList{

        Node head ;
        Node tail ;


        DLinkedList(){
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }


        public Node add(int x){
            Node node = new Node(x);
            Node prev = tail.prev;
            prev.next = node;
            node.prev = prev;
            node.next = tail;
            tail.prev = node;
            return node;
        }


        public Node removeLast(){
            Node node = tail.prev;
            this.remove(node);
            return node;
        }


        public Node getLast(){
            return tail.prev;
        }


        public void  remove(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }
}
