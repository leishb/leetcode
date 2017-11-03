package com.leishb.leetcode.array;

/**
 * Created by me on 2017/11/3.
 */
public class SortColors {




    /**
     * Accepted
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] colors = new int[3];
        for (int i=0;i<nums.length;i++){
            colors[nums[i]]++;
        }
        int red = colors[0];
        int whilte = colors[1];
        int blue = colors[2];
        int i=0;
        while(red>0){
            nums[i++]=0;
            red--;
        }
        while(whilte>0){
            nums[i++]=1;
            whilte--;
        }
        while(blue>0){
            nums[i++]=2;
            blue--;
        }
    }


    public void sortColors2(int[] nums) {

    }


    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
