package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by me on 2019/12/27.
 */
public class _895_Maximum_Frequency_Stack {


    /**
     * Accepted
     */
    static class FreqStack {

        Map<Integer, Item> map ;
        PriorityQueue<Item> pq;
        int time;

        public FreqStack() {
            map = new HashMap<>();
            pq = new PriorityQueue<>();
            this.time = 1;
        }

        public void push(int x) {
            if (map.containsKey(x)){
                Item item = map.get(x);
                pq.remove(item);
                item.freq +=1;
                item.stack.push(time++);
                pq.offer(item);
            }else {
                Item item = new Item(x, time++);
                map.put(x, item);
                pq.offer(item);
            }
        }

        public int pop() {
            Item item = pq.poll();
            item.freq-=1;
            item.stack.pop();
            if (item.freq > 0){
                pq.offer(item);
            }else {
                map.remove(item.value);
            }
            return item.value;
        }


        class Item implements Comparable<Item>{
            int value;
            int freq;
            Stack<Integer> stack = new Stack<>();

            Item(int val, int time){
                this.value = val;
                this.freq = 1;
                stack.push(time);
            }

            @Override
            public int compareTo(Item o) {
                if (this.freq!=o.freq) return o.freq-this.freq;
                else return o.stack.peek() - this.stack.peek();
            }
        }
    }


    public static void main(String[] args){
        FreqStack fs = new FreqStack();
        fs.push(5);
        fs.push(7);
        fs.push(5);
        fs.push(7);
        fs.push(4);
        fs.push(5);

        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
    }

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
}
