package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2017/11/3.
 */
public class SortColors {


    @Test
    public void test(){
        sortColors2(new int[]{1,0});
    }



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


    /**
     * Accepted
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int red = -1;
        int white = -1;
        int blue = -1;
        for (int i=0;i<nums.length;i++){
            if(nums[i]==0){
                nums[++blue]=2;
                nums[++white]=1;
                nums[++red]=0;
            }else if (nums[i] ==1){
                nums[++blue]=2;
                nums[++white]=1;
            }else if (nums[i] ==2){
                nums[++blue]=2;
            }
        }
    }

    public void sortColors3(int[] nums) {

    }


    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
