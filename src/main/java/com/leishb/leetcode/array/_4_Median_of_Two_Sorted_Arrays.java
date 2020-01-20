package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/11/8.
 */
public class _4_Median_of_Two_Sorted_Arrays {

    /**
     * Accepted
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length>nums2.length) return findMedianSortedArrays(nums2, nums1);
        int len1 = nums1.length, len2 = nums2.length;
        int start = 0, end = len1;
        while (true){
            int numsFrom1 = (start+end)/2;
            int numsFrom2 = (len1+len2+1)/2-numsFrom1;
            int leftMax1 = numsFrom1>0?nums1[numsFrom1-1]:Integer.MIN_VALUE;
            int leftMax2 = numsFrom2>0?nums2[numsFrom2-1]:Integer.MIN_VALUE;
            int rightMin1 = numsFrom1<len1?nums1[numsFrom1]:Integer.MAX_VALUE;
            int rightMin2 = numsFrom2<len2?nums2[numsFrom2]:Integer.MAX_VALUE;
            if (leftMax1<=rightMin2 && leftMax2 <=rightMin1){
                if ((len1+len2)%2!=0) return Math.max(leftMax1, leftMax2);
                return (Math.max(leftMax1, leftMax2)+Math.min(rightMin1, rightMin2))/2.0;
            }
            if (leftMax1>rightMin2){//
                end = numsFrom1-1;
            }else if (leftMax2>rightMin1){
                start = numsFrom1+1;
            }
        }
    }


    /**
     * Accepted
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len = nums1.length+nums2.length;
        int k = len/2;
        if (len%2==0){
            return (findKth(nums1, 0, nums2, 0, k) + findKth(nums1, 0, nums2, 0, k+1))/2.0;
        }
        return findKth(nums1, 0, nums2, 0, k+1);
    }


    private double findKth(int[] nums1, int start1, int[] nums2, int start2, int k){
        if (start1 > nums1.length-1)return nums2[start2 + k - 1];
        if (start2 > nums2.length-1)return nums1[start1 + k - 1];
        if (k==1){
            return Math.min(nums1[start1], nums2[start2]);
        }
        int mid1 = Integer.MAX_VALUE, mid2 = Integer.MAX_VALUE;
        if (start1 + k/2 - 1<nums1.length) mid1 = nums1[start1+k/2-1];
        if (start2 + k/2 - 1<nums2.length) mid2 = nums2[start2+k/2-1];
        if (mid1<mid2){
            return findKth(nums1, start1+k/2, nums2, start2, k-k/2);
        }else {
            return findKth(nums1, start1, nums2, start2+k/2, k-k/2);
        }
    }

    @Test
    public void test(){
        Assert.assertTrue(findMedianSortedArrays2(new int[]{1,2}, new int[]{3,4})==2.5);
        Assert.assertEquals(2.5, findMedianSortedArrays2(new int[]{1,2}, new int[]{3,4}), 0.1);

        System.out.println(findKth(new int[]{1,2}, 0,  new int[]{3,4}, 0, 2));
        System.out.println(findKth(new int[]{1,2}, 0,  new int[]{3,4}, 0, 3));
    }
}
