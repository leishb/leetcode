package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/14.
 */
public class _239_Sliding_Window_Maximum {



    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length==0) return new int[0];
        int[] ans = new int[nums.length-k+1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i=0;i<k;i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        ans[0] = map.lastKey();
        for (int i=k;i<nums.length;i++){
            map.put(nums[i-k], map.get(nums[i-k])-1);
            if (map.get(nums[i-k])==0) map.remove(nums[i-k]);
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            ans[i-k+1] = map.lastKey();
        }
        return ans;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length==0) return new int[0];
        int[] ans = new int[nums.length-k+1];
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i=0;i<k;i++){
            if (nums[i] >= max){
                max = nums[i];
                maxIndex = i;
            }
        }
        ans[0] = max;
        for (int i=k;i<nums.length;i++){
            if (nums[i] >= max){
                max = nums[i];
                maxIndex = i;
            }else if (maxIndex == i-k){
                max = nums[i];
                maxIndex = i;
                for (int j=i-k+1;j<=i;j++){
                    if (nums[j] >= max){
                        max = nums[j];
                        maxIndex = j;
                    }
                }
            }
            ans[i-k+1] = max;
        }
        return ans;
    }


    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums.length==0) return new int[0];
        int[] ans = new int[nums.length-k+1];
        Deque<Integer> dq = new LinkedList<>();
        for (int i=0;i<nums.length;i++){
            while (!dq.isEmpty() && nums[i] > nums[dq.getLast()]){
                dq.removeLast();
            }
            dq.addLast(i);
            if (i-k>=0 && dq.getFirst() == i-k){
                dq.removeFirst();
            }
            if (i-k+1>=0) ans[i-k+1] = nums[dq.getFirst()];
        }
        return ans;
    }

    public int[] maxSlidingWindow4(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>((a,b)->nums[b]-nums[a]);
        int[] res = new int[nums.length-k+1];
        for (int i=0;i<k;i++){
            queue.offer(i);
        }
        int j = 0;
        res[j++] = nums[queue.peek()];
        for (int i=k;i<nums.length;i++){
            queue.remove(i-k);
            queue.offer(i);
            res[j++] = nums[queue.peek()];
        }
        return res;
    }
    @Test
    public void test(){
        maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }
}
