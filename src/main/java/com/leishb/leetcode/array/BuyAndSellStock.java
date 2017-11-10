package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/8.
 */
public class BuyAndSellStock {

    @Test
    public void test(){
        Assert.assertTrue(maxProfit2(new int[]{7,1,5,3,6,4})==5);
        Assert.assertTrue(maxProfit2(new int[]{7,1,5,3,6})==maxProfit(new int[]{7,1,5,3,6}));
        Assert.assertTrue(maxProfit2(new int[]{7,1,5,2,9,8})==maxProfit(new int[]{7,1,5,2,9,8}));
        Assert.assertTrue(maxProfit2(new int[]{1,1,5,3,6,7})==maxProfit(new int[]{1,1,5,3,6,7}));


        Assert.assertTrue(maxProfit4(new int[]{3,3,5,0,0,3,1,4})==6);
        Assert.assertTrue(maxProfit5(new int[]{3,3,5,0,0,3,1,4})==6);
    }


    /**
     * Accepted
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int[] diff = new int[prices.length-1];
        for(int i=0;i<diff.length;i++){
            diff[i] = prices[i+1] - prices[i];
        }
        int profit = 0;
        int sum = 0;
        for(int i=0;i<diff.length;i++){
            sum += diff[i];
            profit = Math.max(sum, profit);
            if (sum < 0){
                sum=0;
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int profit = 0;
        int sum = 0;
        for (int i=1;i<prices.length;i++){
            sum = Math.max(sum, 0) + (prices[i] - prices[i-1]);
            profit = Math.max(profit, sum);
        }
        return profit;
    }


    /**
     * Time limit exceeded
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int result = 0;
        for (int i=0;i<prices.length;i++){
            result = Math.max(helper(prices, 0, i)+ helper(prices, i, prices.length-1), result);
        }
        return result;
    }

    private int helper(int[] prices, int start, int end){
        int profit = 0;
        int sum = 0;
        for (int i=start+1;i<=end;i++){
            sum = Math.max(sum, 0) + (prices[i] - prices[i-1]);
            profit = Math.max(profit, sum);
        }
        return profit;
    }


    /**
     * Accepted
     * @param prices
     * @return
     */
    @DynamicProgramming
    public int maxProfit4(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int result = 0;
        int[] dp1 = new int[prices.length];
        dp1[0]=0;
        int sum = 0;
        for (int i=1;i<prices.length;i++){
            int diff = prices[i]-prices[i-1];
            sum = Math.max(sum, 0)+diff;
            dp1[i] = Math.max(sum, dp1[i-1]);
        }
        int[] dp2 = new int[prices.length];
        sum = 0;
        dp2[prices.length-1]=0;
        for (int i=prices.length-2;i>=0;i--){
            int diff = prices[i]-prices[i+1];
            sum = Math.min(sum, 0) + diff;
            dp2[i]=Math.min(dp2[i+1], sum);
        }
        for (int i=0;i<prices.length;i++){
            result = Math.max(result, Math.max(0, dp1[i])-Math.min(0, dp2[i]));
        }
        return result;
    }


    /**
     * Accepted
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int result = 0;
        int[] dp1 = new int[prices.length];
        dp1[0]=0;
        int sum = 0;
        for (int i=1;i<prices.length;i++){
            sum = Math.max(sum, 0)+prices[i]-prices[i-1];
            dp1[i] = Math.max(sum, dp1[i-1]);
        }
        sum = 0;
        int curMin = Integer.MAX_VALUE;
        for (int i=prices.length-2;i>=0;i--){
            sum = Math.min(sum, 0) + prices[i]-prices[i+1];
            curMin = Math.min(curMin, sum);
            result = Math.max(result, Math.max(0, dp1[i])-Math.min(0, curMin));
        }
        result = Math.max(result, dp1[prices.length-1]);
        return result;
    }

}
