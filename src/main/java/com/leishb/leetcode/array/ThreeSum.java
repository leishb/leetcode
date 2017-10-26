package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/10/23.
 */
@TwoPointers
public class ThreeSum {


    @Test
    public void test(){
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{0,0,0,0}));

        Assert.assertTrue(threeSumCloset(new int[]{0,0,0},1)==0);
        Assert.assertTrue(threeSumCloset(new int[]{-1, 2, 1, -4},1)==2);
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i=0;i<nums.length-2;i++){
            int first = i;
            int second = i+1;
            int last = nums.length-1;
            if (first!=0 && nums[first] == nums[first-1]){
                continue;
            }
            while (second < last){
                if (second != i+1 && nums[second] == nums[second-1]){
                    second ++;
                    continue;
                }
                if (last != nums.length-1 && nums[last] == nums[last+1]){
                    last--;
                    continue;
                }
                int sum = nums[first] + nums[second] + nums[last];
                if (sum > 0){
                    last--;
                }
                if (sum <0) {
                    second++;
                }
                if (sum==0){
                    result.add(Arrays.asList(nums[first], nums[second], nums[last]));
                    last--;
                }
            }
        }
        return result;
    }


    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length<4){
            return result;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            int first = i;
            if (i!=0 && nums[i] == nums[i-1]){
                continue;
            }
            for (int j=i+1;j<nums.length;j++){
                if (j!=i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                int second = j;
                int third = second+1;
                int last = nums.length-1;
                while (third<last){
                    if (third != second+1 && nums[third] == nums[third-1]){
                        third++;
                        continue;
                    }
                    if (last!=nums.length-1 && nums[last] == nums[last+1]){
                        last--;
                        continue;
                    }
                    int sum = nums[first] + nums[second] + nums[third] + nums[last];
                    if (sum > target){
                        last--;
                    }
                    if (sum < target){
                        third++;
                    }
                    if (sum==target){
                        result.add(Arrays.asList(nums[first],nums[second], nums[third] , nums[last]));
                        last--;
                    }
                }
            }
        }
        return result;
    }


    /**
     * Accepted
     * A = [ 1, 2]
     B = [-2,-1]
     C = [-1, 2]
     D = [ 0, 2]
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A,int[] B,int[] C,int[] D){
        int count = 0;
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
        }
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                int sum = C[i] + D[j];
                count+=map.getOrDefault(0-sum,0);
            }
        }
        return count;
    }


    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public int threeSumCloset(int[] nums, int target){
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length-1];
        for (int i=0;i<nums.length-2;i++){
            int first = i;
            int second = first+1;
            int last = nums.length -1;
            while (second < last){
                int sum = nums[first] + nums[second] + nums[last];
                if (sum > target){
                    last--;
                }
                if (sum < target){
                    second++;
                }
                if (sum == target){
                    return target;
                }
                if (Math.abs(result-target) > Math.abs(sum-target)){
                    result = sum;
                }
            }
        }
        return result;
    }
}
