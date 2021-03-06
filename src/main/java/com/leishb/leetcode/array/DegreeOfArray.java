package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/11/24.
 */
public class DegreeOfArray {

    @Test
    public void test(){
        Assert.assertTrue(findShortestSubArray2(new int[]{1, 2, 2, 3, 1})==2);
        Assert.assertTrue(findShortestSubArray2(new int[]{1,2,2,3,1,4,2})==6);
        Assert.assertTrue(findShortestSubArray2(new int[]{1})==1);
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer,Integer> map = new HashMap();
        int degree = 0;
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
            degree = Math.max(degree, map.get(num));
        }
        int low =1;
        int high=nums.length;
        while (low<=high){
            int mid = (high+low)/2;
            map.clear();
            boolean exist=false;
            int j=0;
            for (int i=0;i<nums.length;i++){
                map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
                if (map.get(nums[i])==degree){
                    exist=true;
                    break;
                }
                if (i-j+1>=mid){
                    map.put(nums[j], map.get(nums[j])-1);
                    j++;
                }
            }
            if (exist){
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        return low;
    }

    public int findShortestSubArray2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        int degree = 0;
        for (int i =0;i<nums.length;i++){
            freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1);
            first.putIfAbsent(nums[i], i);
            last.put(nums[i], i);
            degree = Math.max(freq.get(nums[i]), degree);
        }
        int ans = nums.length;
        for (int k : freq.keySet()){
            if (freq.get(k)==degree){
                ans = Math.min(ans, last.get(k)-first.get(k)+1);
            }
        }
        return ans;
    }
}
