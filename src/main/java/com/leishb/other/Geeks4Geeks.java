package com.iqiyi.pay.cashier.alg;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Geeks4Geeks {


    public int solve(int[] nums, int k){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i=1;i<n;i++){
            dp[i] = Math.max(dp[i-1], i> k ? dp[i-k-1] + nums[i] : nums[i]);
        }
        return dp[n-1];
    }


    public int maxAfterIncrement(int n , int[][] range){
        int[] arr = new int[n + 1];
        for (int[] r : range){
            arr[r[0]] += r[2];
            arr[r[1]+1] -= r[2];
        }
        int ans = 0, sum = 0;
        for (int i=0;i<n;i++){
            sum += arr[i];
            ans = Math.max(ans, sum);
        }
        return ans;
    }


    public int maxSubArraySum(int[] nums, int k){
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] dp = new int[n];
        for (int i=0;i<n;i++){
            dp[i] = Math.max(nums[i], i>=k ? dp[i-k] + nums[i] : nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }


    private List<String> dfs(String s, List<String> dict, Map<String, List<String>> memo){
        if (s.length()==0){
            return Arrays.asList("");
        }
        if (memo.containsKey(s)) return memo.get(s);
        List<String> list = new ArrayList<>();
        for (String word : dict){
            if (!s.startsWith(word)) continue;
            List<String> next = dfs(s.substring(word.length()), dict, memo);
            for (String ss : next){
                list.add(word + (ss.length() ==0 ? "" : (" " + ss)));
            }
        }
        memo.put(s, list);
        return list;
    }



    public int mergeTwoNumbers(int[] nums){
        int n = nums.length;
        Integer[][] dp = new Integer[n][n];
        int[] sums = new int[n+1];
        for (int i=0;i<n;i++){
            sums[i+1] = sums[i] + nums[i];
        }
        return dfs(0, n - 1, sums, dp);
    }


    private int dfs(int start, int end, int[] sums, Integer[][] dp){
        if (start == end) return 0;
        if (dp[start][end] != null) return dp[start][end];
        int ans = Integer.MAX_VALUE;
        for (int i=start;i<end;i++){
            ans = Math.min(ans, dfs(start, i, sums, dp) + dfs(i+1, end, sums, dp) + sums[end + 1] - sums[start]);
        }
        return dp[start][end] = ans;
    }



    @Test
    public void test(){
        System.out.println(solve(new int[]{5, 3, 4, 11, 2}, 1));
        System.out.println(solve(new int[]{6, 7, 1, 3, 8, 2, 4}, 2));
        System.out.println(maxAfterIncrement(5, new int[][]{{0, 1 , 100}, {1, 4, 100}, {2, 3, 100}}));
        System.out.println(maxAfterIncrement(4, new int[][]{{1, 2 , 603}, {0, 0, 286}, {3, 3, 882}}));
        Assert.assertEquals(3, maxSubArraySum(new int[]{2, -3, -1, -1, 2}, 2));
        Assert.assertEquals(5, maxSubArraySum(new int[]{2, 3, -1, -1, 2}, 3));
        Assert.assertEquals(40, mergeTwoNumbers(new int[]{6,4,4,6}));
    }
}
