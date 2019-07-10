package com.leishb.leetcode.array;

/**
 * Created by me on 2019/7/10.
 */
public class _477_Total_Hamming_Distance {

    public int totalHammingDistance(int[] nums) {
        int total = 0;
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                total += hammingDistance(nums[i], nums[j]);
            }
        }
        return total;
    }

    public int totalHammingDistance2(int[] nums) {
        int total = 0;
        for (int i=0;i<32;i++){
            int bitCount = 0;
            for (int j=0;j<nums.length;j++){
                if (((1<<i)&nums[i])!=0){
                    bitCount++;
                }
            }
            total += bitCount * (nums.length-bitCount);
        }
        return total;
    }

    public int hammingDistance(int x, int y) {
        int k = x ^ y;
        int count = 0;
        for(int i=0;i<32;i++){
            int m = 1 << i;
            if((m & k)!=0){
                count++;
            }
        }
        return count;
    }
}
