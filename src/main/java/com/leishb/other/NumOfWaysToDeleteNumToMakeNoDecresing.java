package com.leishb.other;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2020/3/18.
 */
public class NumOfWaysToDeleteNumToMakeNoDecresing {


    /**
     * https://leetcode.com/discuss/interview-question/542902/Number-of-ways-to-delete-a-number-from-an-array-to-maintain-the-array-in-non-decreasing-order
     * @param nums
     * @return
     */
    public int numOfWaysToDeleteNums(int[] nums){
        if(nums.length <3) return nums.length;
        int n = nums.length;
        boolean[] leftOrder = new boolean[n], rightOrder = new boolean[n];
        leftOrder[0] = true;
        for(int i=1;i<n;i++){
            if(nums[i] >= nums[i-1] && leftOrder[i-1]) leftOrder[i] = true;
        }
        rightOrder[n-1] = true;
        for(int i=n-2;i>=0;i--){
            if(nums[i] <= nums[i+1] && rightOrder[i+1]) rightOrder[i] = true;
        }
        int ans = 0;
        for(int i=0;i<n;i++){
            if(i==0){
                if(rightOrder[i+1]) ans++;
                else continue;
            }else if(i==n-1){
                if(leftOrder[i-1]) ans++;
                else continue;
            }else if(nums[i-1] <= nums[i+1] && leftOrder[i-1] && rightOrder[i+1]){
                ans++;
            }
        }
        return ans;
    }



    public List<Integer> solve(int[] nums){
        List<Integer> ans = new ArrayList<>();
        dfs(ans, 0, 1, nums);
        System.out.println(ans);
        Collections.sort(ans);
        System.out.println(ans);
        return ans;
    }



    private void dfs(List<Integer> ans, int start, int num,  int[] nums){
        for (int i=start;i<nums.length;i++){
            ans.add(num * nums[i]);
            dfs(ans, i + 1,  num * nums[i], nums);
        }
    }


    @Test
    public void test(){
        solve(new int[]{3, 5, 7});
    }
}
