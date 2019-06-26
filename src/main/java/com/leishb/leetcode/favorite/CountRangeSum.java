package com.leishb.leetcode.favorite;

import com.leishb.leetcode.tag.MergeSort;

/**
 * Created by me on 2019/6/19.
 */
@MergeSort
public class CountRangeSum {


    /**
     * Accepted
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length+1];
        for (int i=0;i<nums.length;i++){
            sum[i+1] = sum[i] + nums[i];
        }
        return mergeSort(sum, 0, sum.length-1, lower, upper);
    }


    private int mergeSort(long[] sum, int start, int end, int lower, int upper){
        if (start<end){
            int mid = (start+end)/2;
            int left = mergeSort(sum, start, mid, lower, upper);
            int right = mergeSort(sum, mid+1, end, lower, upper);
            return  left + right + merge(sum, start, mid, end, lower, upper);
        }
        return 0;
    }


    private int merge(long[] sum, int start, int mid, int end, int lower, int upper){
        int count = 0;
        for (int i = start;i<=mid;i++){
            int m = mid+1;
            while (m<=end && sum[m] - sum[i]<lower){
                m++;
            }
            int n = end;
            while (n>=mid+1 && sum[n] - sum[i] >upper){
                n--;
            }
            if (n>=m){
                count += n-m+1;
            }
        }
        int i = start;
        int j = mid+1;
        int k = 0;
        long[] temp = new long[end-start+1];
        while (i<=mid && j<=end){
            if (sum[i] <= sum[j]){
                temp[k++] = sum[i++];
            }else {
                temp[k++] = sum[j++];
            }
        }
        while (i<=mid){
            temp[k++] = sum[i++];
        }
        while (j<=end){
            temp[k++] = sum[j++];
        }
        for (i=0;i<temp.length;i++){
            sum[start+i] = temp[i];
        }
        return count;
    }
}
