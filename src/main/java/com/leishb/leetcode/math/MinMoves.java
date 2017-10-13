package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/12.
 */
public class MinMoves {

    @Test
    public void test(){
//        Assert.assertTrue(minMoves(new int[]{1,2,3})==3);
        Assert.assertTrue(minMoves2(new int[]{3,1,2})==2);
        Assert.assertTrue(minMoves2(new int[]{2,3,1})==2);
        Assert.assertTrue(minMoves2(new int[]{1,0,0,8,6})==14);
    }


    /**
     * Accepted
     * sum + k * (n-1) = m * n, k is the moves, [m,m,m,m...]. minNum + k = m. sum+ k*(n-1)=(minNum+k)*n; k=sum-minNum*n;
     * 数学问题，可以考虑总加和 sum
     * @param nums
     * @return
     */
    public int minMoves(int[] nums){
        int sum = nums[0];
        int min = nums[0];
        for (int i=1;i<nums.length;i++){
            sum += nums[i];
            if (nums[i]<min){
                min = nums[i];
            }
        }
        int count = sum - min*nums.length;
        return count;
    }

    /**
     * Accepted
     * @param A
     * @return
     */
    public int minMoves2(int[] A) {
        int sum = 0, median = quickselect(A, A.length/2+1, 0, A.length-1);
        for (int i=0;i<A.length;i++) sum += Math.abs(A[i] - median);
        return sum;
    }

    /**
     * atention bindary condition
     * @param nums
     * @param k
     * @param start
     * @param end
     * @return
     */
    public int quickselect(int[] nums, int k, int start, int end) {
        int l = start, r = end, pivot = nums[(l+r)/2];
        while (l<=r) {//attention '='
            while (nums[l] < pivot) l++;
            while (nums[r] > pivot) r--;
            if (l>=r) break;
            swap(nums, l, r);
            l++;
            r--;
        }
        if (l+1 > k) return quickselect(nums, k, start, l-1);
        if (l+1 == k && l==r) return nums[l];
        return quickselect(nums, k, r+1, end);
    }


    public void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
