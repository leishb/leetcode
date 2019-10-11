package com.leishb.leetcode.array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/9/27.
 */
public class _480_Sliding_Window_Median {

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length==0) return new double[0];
        int n = nums.length;
        double[] ans = new double[n-k+1];
        if (k==1){
            for (int i=0;i<n;i++){
                ans[i] = nums[i];
            }
            return ans;
        }
        Queue<Long> left = new PriorityQueue<>((l1, l2)->Long.compare(l2, l1));
        Queue<Long> right = new PriorityQueue<>((l1, l2)->Long.compare(l1, l2));
        for (int i=0;i<k;i++){
            left.offer((long) nums[i]);
        }
        while (right.size() < k/2){
            right.offer(left.poll());
        }
        int j=0;
        ans[j++] = k%2==0?(left.peek()+right.peek())/2.0:left.peek();
        for (int i=k;i<n;i++){
            if (nums[i-k]<=left.peek()){
                left.remove((long)nums[i-k]);
                left.offer((long) nums[i]);
            }else {
                right.remove((long)nums[i-k]);
                right.offer((long) nums[i]);
            }
            if (left.peek() > right.peek()){
                right.offer(left.poll());
                left.offer(right.poll());
            }
            ans[j++] = k%2==0?(left.peek()+right.peek())/2.0:left.peek();
        }
        return ans;
    }



}
