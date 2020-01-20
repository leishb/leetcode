package com.leishb.leetcode.array;

/**
 * Created by me on 2020/1/19.
 */
public class _665_Non_decreasing_Array {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        if (nums.length<=2) return true;
        return dfs(nums, 0, 0);
    }


    private boolean dfs(int[] nums, int index, int count){
        if (index>0 && index<nums.length && nums[index-1] > nums[index]){
            nums[index] = nums[index-1];
            return count==0 &&  dfs(nums, index+1, count+1);
        }
        if (index>=nums.length-1){
            return true;
        }
        if (nums[index]<=nums[index+1]) return dfs(nums, index+1, count);
        if (count>=1) return false;
        //two choices, 1 : change current, 2 : change next
        int cur = nums[index];
        if (index==0){
            nums[index] = nums[index+1];
            if (dfs(nums, index+1, count+1)){
                return true;
            }
            nums[index] = cur;
        }else {
            if (nums[index-1] > nums[index+1]){
                return dfs(nums, index+1, count);
            }
            nums[index] = nums[index-1];
            if (dfs(nums, index+1, count+1)){
                return true;
            }
            nums[index] = cur;
        }
        //change next
        int next = nums[index+1];
        nums[index+1] = nums[index];
        if (dfs(nums, index+1, count+1)){
            return true;
        }
        nums[index+1] = next;
        return false;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean checkPossibility2(int[] nums) {
        if (nums.length<=2) return true;
        boolean found = false;
        for (int i=1;i<nums.length;i++){
            if (nums[i] < nums[i-1]){
                if (found) return false;
                if (i>=2 && nums[i-2] > nums[i]){
                    nums[i] = nums[i-1];
                }else {
                    nums[i-1] = i>=2 ? nums[i-2] : nums[i];
                }
                found = true;
            }
        }
        return true;
    }
}
