package com.leishb.leetcode.favorite;

/**
 * Created by me on 2019/6/18.
 */
public class NumArray {

    int[] bitArr;

    int[] nums ;


    public NumArray(int[] nums) {
        bitArr = new int[nums.length+1];
        this.nums = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            update(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        int idx = i+1;
        int delt = val - nums[i];
        this.nums[i] = val;
        while (idx<this.bitArr.length){
            this.bitArr[idx] += delt;
            idx = idx + lowbit(idx);
        }
    }

    private int getSum(int i){
        int idx = i+1;
        int ans = 0;
        while (idx>0){
            ans += this.bitArr[idx];
            idx = idx - lowbit(idx);
        }
        return ans;
    }

    private int lowbit(int x){
        return x & -x;
    }

    public int sumRange(int i, int j) {
        return getSum(j)-getSum(i-1);
    }
}
