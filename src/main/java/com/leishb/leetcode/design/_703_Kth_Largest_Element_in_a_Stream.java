package com.leishb.leetcode.design;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2020/1/8.
 */
public class _703_Kth_Largest_Element_in_a_Stream {

    class KthLargest {

        Queue<Integer> minHeap;
        int k ;
        public KthLargest(int k, int[] nums) {
             minHeap = new PriorityQueue<>();
            this.k = k;
            for (int num : nums){
                addElement(num);
            }
        }

        public void addElement(int num){
            if ( minHeap.size()<k){
                 minHeap.offer(num);
            }else if ( minHeap.peek() < num){
                minHeap.poll();
                minHeap.offer(num);
            }
        }

        public int add(int val) {
            addElement(val);
            return minHeap.peek();
        }
    }
}
