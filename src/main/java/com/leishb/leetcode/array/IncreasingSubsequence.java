package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/5/29.
 */
@DynamicProgramming
public class IncreasingSubsequence {


    /**
     * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
     * Accepted
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        int max = 1;
        for(int i=0;i<nums.length;i++){
            dp[i] = 1;
            count[i] = 1;
            for(int j=i-1;j>=0;j--){
                if(nums[i] > nums[j]){
                    if (dp[j]+1 == dp[i]){
                        count[i] += count[j];
                    }
                    if(dp[j]+1 > dp[i]){
                        dp[i] = dp[j]+1;
                        count[i] = count[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int result = 0;
        for (int i=0;i<nums.length;i++){
            if (dp[i]==max){
                result += count[i];
            }
        }
        return result;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxNum = 1;
        for(int i=1;i<nums.length;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                int temp = dp[j] +1;
                if(nums[i] > nums[j]){
                    if(temp > dp[i]){
                        dp[i] = temp;
                    }
                }
            }
            maxNum = Math.max(maxNum, dp[i]);
        }
        return maxNum;
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums){
            int i=0;
            int j=size;
            while (i<j){
                int m = (i+j)/2;
                if (tails[m] < num){
                    i = m+1;
                }else {
                    j = m;
                }
            }
            tails[i] = num;
            if (i==size)size++;
        }
        return size;
    }

    /**
     * Accepted
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int[] dp = new int[pairs.length];
        int max = 0;
        for(int i=0;i<pairs.length;i++){
            dp[i] = 1;
            for(int j=i-1;j>=0;j--){
                if(pairs[j][1] < pairs[i][0]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    break;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * Accepted
     * @param pairs
     * @return
     */
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        int cur = Integer.MIN_VALUE;
        int ans = 0;
        for (int i=0;i<pairs.length;i++){
            if (cur < pairs[i][0]){
                cur = pairs[i][1];
                ans++;
            }
        }
        return ans;
    }

    /**
     * Accepted
     * https://leetcode.com/problems/increasing-subsequences
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        backtracking(nums, 0, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }


    private void backtracking(int[] nums, int start, List<Integer> list, Set<List<Integer>> result){
        if (list.size()>1){
            result.add(new ArrayList<>(list));
        }
        for (int i=start;i<nums.length;i++){
            if (list.size()==0 || nums[i] >= list.get(list.size()-1)){
                if (i!=start && nums[i] == nums[i-1]){
                    continue;
                }
                list.add(nums[i]);
                backtracking(nums, i+1, list, result);
                list.remove(list.size()-1);
            }
        }
    }


    /**
     * Accepted
     * https://leetcode.com/problems/increasing-subsequences
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(nums, 0, new ArrayList<>(), result);
        return result;
    }


    private void backtracking(int[] nums, int start, List<Integer> list, List<List<Integer>> result){
        if (list.size()>1){
            result.add(new ArrayList<>(list));
        }
        Set<Integer> used = new HashSet<>();
        for (int i=start;i<nums.length;i++){
            if (list.size()==0 || nums[i] >= list.get(list.size()-1)){
                if (used.contains(nums[i])) {
                    continue;
                }
                used.add(nums[i]);
                list.add(nums[i]);
                backtracking(nums, i+1, list, result);
                list.remove(list.size()-1);
            }
        }
    }

    @Test
    public void test(){
        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
        System.out.println(findNumberOfLIS(new int[]{2,2,2,2,2}));
        System.out.println(findNumberOfLIS(new int[]{84,-48,-33,-34,-52,72,75,-12,72,-45}));

        System.out.println(findLongestChain(new int[][]{{3,4},{2,3},{1,2}}));
        System.out.println(findLongestChain2(new int[][]{{3,4},{2,3},{1,2}}));
        System.out.println(findLongestChain2(new int[][]{{1,100},{2,3},{5,6}}));
        System.out.println(findSubsequences(new int[]{4,1,6,6,7}));
        System.out.println(findSubsequences(new int[]{4,1,6,6,7,1,6}));
        System.out.println(findSubsequences(new int[]{-8,28,68,-54,96,97,84,-32,8,-87,1,-7,-20,12,22}));
    }
}
