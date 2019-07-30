package com.leishb.leetcode.array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/7/30.
 */
public class _295_Find_Median_from_Data_Stream {


    Queue<Integer> left ;
    Queue<Integer> right ;


    /**
     * Accepted
     */
    public _295_Find_Median_from_Data_Stream() {
        left = new PriorityQueue<>((a, b)->Integer.compare(b, a));
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left.size()==right.size()){
            left.offer(num);
            right.offer(left.poll());
        }else {
            right.offer(num);
            left.offer(right.poll());
        }
    }

    public double findMedian() {
        if ((left.size()+right.size())%2==0){
            return (double)(left.poll()+right.poll())/2.0;
        }else {
            return right.poll();
        }
    }
}
