package com.leishb.leetcode.slidewindow;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/11/6.
 */
public class _487_Max_Consecutive_Ones_II {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, zeros = 0, j=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0) zeros+=1;
            while (zeros>1){
                if (nums[j]==0) zeros-=1;
                j++;
            }
            res = Math.max(res, i-j+1);
        }
        return res;
    }


    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0, k=1, j = 0;
        Queue<Integer> zeroIndexes = new LinkedList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                zeroIndexes.offer(i);
            }
            while (zeroIndexes.size() > k){
                j = zeroIndexes.poll()+1;
            }
            res = Math.max(res, i-j+1);
        }
        return res;
    }
}
