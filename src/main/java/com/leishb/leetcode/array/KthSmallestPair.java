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


    /**
     * TLE
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2]));
        for (int i=0;i<nums.length-1;i++){
            queue.offer(new int[]{i, i+1, nums[i=1]-nums[i]});
        }
        int ans = 0;
        while (k-->0 && !queue.isEmpty()){
            int[] arr = queue.poll();
            ans = arr[2];
            if (arr[1]<nums.length-1){
                queue.offer(new int[]{arr[0], arr[1]+1, nums[arr[1]+1]-nums[arr[0]]});
            }
        }
        return ans;
    }
}
