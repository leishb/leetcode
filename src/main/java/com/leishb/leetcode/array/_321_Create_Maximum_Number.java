package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/15.
 */
public class _321_Create_Maximum_Number {

    /**
     * Accepted
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        int len1 = nums1.length;
        int len2 = nums2.length;
        int getNumFrom1 = Math.min(k, len1);
        for (int i=Math.max(0, k-len2);i<=getNumFrom1;i++){
            int[] arr1 = maxSubArray(nums1, i);
            int[] arr2 = maxSubArray(nums2, k-i);
            int[] arr = merge(arr1, arr2);
            if (compare(arr, ans, 0, 0)){
                ans = arr;
            }
        }
        return ans;
    }


    private int[] merge(int[] arr1, int[] arr2){
        int i = 0, j=0, k=0;
        int[] arr = new int[arr1.length+arr2.length];
        while (i< arr1.length && j<arr2.length){
            while (i< arr1.length && compare(arr1, arr2, i, j)){
                arr[k++] = arr1[i++];
            }
            while (j< arr2.length &&  !compare(arr1, arr2, i, j)){
                arr[k++] = arr2[j++];
            }
        }
        while (i< arr1.length){
            arr[k++] = arr1[i++];
        }
        while (j< arr2.length){
            arr[k++] = arr2[j++];
        }
        return arr;
    }


    private boolean compare(int[] arr1, int[] arr2, int s1, int s2){
        for (;s1<arr1.length&&s2<arr2.length;s1++,s2++){
            if (arr1[s1] > arr2[s2]) return true;
            if (arr1[s1] < arr2[s2]) return false;
        }
        return s1!=arr1.length;
    }


    private int[] maxSubArray(int[] nums, int k){
        int[] res = new int[k];
        int len = 0;
        for (int i=0;i<nums.length;i++){
            while (len > 0 && nums.length-i > k-len && res[len-1] < nums[i]){
                len--;
            }
            if (len<k){
                res[len++] = nums[i];
            }
        }
        return res;
    }
}
