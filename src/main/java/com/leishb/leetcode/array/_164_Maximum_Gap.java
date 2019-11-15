package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/30.
 */
public class _164_Maximum_Gap {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums.length<=1) return 0;
        int max = nums[0];
        for (int i=1;i<nums.length;i++){
            max = Math.max(max, nums[i]);
        }
        int exp = 1;
        int radix = 10;
        int[] aux = new int[nums.length];
        while (max/exp!=0){
            int[] count = new int[radix];
            for (int i=0;i<nums.length;i++){
                count[(nums[i]/exp)%10]++;
            }
            for (int i=1;i<count.length;i++){
                count[i] += count[i-1];
            }
            for (int i=nums.length-1;i>=0;i--){
                aux[--count[(nums[i]/exp)%10]] = nums[i];
            }
            for (int i=0;i<nums.length;i++){
                nums[i] = aux[i];
            }
            exp *=10;
        }
        int maxGap = 0;
        for (int i=1;i<nums.length;i++){
            maxGap = Math.max(maxGap, nums[i]-nums[i-1]);
        }
        return maxGap;
    }
}
