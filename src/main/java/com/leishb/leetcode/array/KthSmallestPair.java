package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import com.leishb.leetcode.tag.TwoPointers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by me on 2017/11/16.
 */
@BinarySearch
@TwoPointers
public class KthSmallestPair {


    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length-1] - nums[0];

        while (low < high){
            int mid = (low+high)/2;
            int count=0;
            for (int i=0;i<nums.length;i++){
                int j=i+1;
                while (j<nums.length && nums[j]-nums[i]<=mid){
                    count++;
                    j++;
                }
            }

            if (count <k){
                low=mid+1;
            }else {
                high=mid;
            }

        }
        return low;
    }

    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair3(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length-1] - nums[0];
        while (low < high){
            int mid = (low+high)/2;
            int count=0;
            int j=0;
            for (int i=0;i<nums.length;i++){
                while (j<nums.length && nums[j]-nums[i]<=mid){
                    j++;
                }
                count += j-i-1;
            }
            if (count <k){
                low=mid+1;
            }else {
                high=mid;
            }
        }
        return low;
    }


    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Pair> heap = new PriorityQueue<>(nums.length, (Comparator<Pair>) (o1, o2) -> (o1.next-o1.cur)-(o2.next-o2.cur));
        for (int i=0;i<nums.length-1;i++){
            heap.offer(new Pair(nums[i], nums[i+1]));
        }
        Pair pair = null;
        for (;k>0;k--){
            pair = heap.poll();
            if (pair.next<nums.length-1){
                heap.offer(new Pair(pair.cur, pair.next+1));
            }
        }
        return nums[pair.next]-nums[pair.cur];
    }

    class Pair{

        Pair(int cur, int next){
            this.cur = cur;
            this.next= next;
        }
        int cur;
        int next;
    }
}
